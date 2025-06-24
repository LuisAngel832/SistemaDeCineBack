package com.example.metrix.controller;

import com.example.metrix.model.Boleto;
import com.example.metrix.model.Compra;
import com.example.metrix.model.Funcion;
import com.example.metrix.repository.BoletoRepository;
import com.example.metrix.repository.CompraRepository;
import com.example.metrix.repository.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@RestController
@RequestMapping("api/boletos")
public class BoletoController {

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private FuncionRepository funcionRepository;

    @CrossOrigin
    @DeleteMapping("/cancelar-boleto/{idBoleto}")
    @Transactional
    public ResponseEntity<?> cancelarBoleto(@PathVariable Integer idBoleto) {
        Optional<Boleto> boletoOptional = boletoRepository.findById(idBoleto);
        if (!boletoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El boleto no existe.");
        }

        Boleto boleto = boletoOptional.get();
        Compra compra = boleto.getCompra();
        Funcion funcion = boleto.getFuncion();

        // Actualizar el dinero recaudado en la función
        double precioBoleto = funcion.getPrecioBoleto();
        funcion.setDineroRecaudado(funcion.getDineroRecaudado() - precioBoleto);

        // Remover el boleto de la lista de boletos vendidos en la función
        funcion.getBoletosVendidos().remove(boleto);
        funcionRepository.save(funcion);

        // Remover el boleto de la compra y actualizar el monto
        compra.getBoletos().remove(boleto);
        compra.setMonto(compra.getMonto() - precioBoleto);

        // Si la compra ya no tiene boletos, eliminar la compra
        if (compra.getBoletos().isEmpty()) {
            compraRepository.delete(compra);
        } else {
            compraRepository.save(compra);
        }

        // Eliminar el boleto
        boletoRepository.delete(boleto);

        return ResponseEntity.ok("El boleto ha sido cancelado exitosamente.");
    }
}
