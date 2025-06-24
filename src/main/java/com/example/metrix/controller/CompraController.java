package com.example.metrix.controller;

import com.example.metrix.model.Boleto;
import com.example.metrix.model.Compra;
import com.example.metrix.model.Funcion;
import com.example.metrix.repository.CompraRepository;
import com.example.metrix.repository.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/compra")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private FuncionRepository funcionRepository;

    @CrossOrigin
    @PostMapping("registrar-compra")
    @Transactional
    public ResponseEntity<?> registrarCompra(@RequestBody Compra compra) {
        // Validar que la función existe
        Optional<Funcion> funcionOptional = funcionRepository.findById(compra.getFuncion().getId());
        if (!funcionOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La función no existe.");
        }

        Funcion funcion = funcionOptional.get();

        // Establecer la función en cada boleto y validar asientos
        List<Boleto> boletos = compra.getBoletos();
        for (Boleto boleto : boletos) {
            boleto.setFuncion(funcion);
            boleto.setCompra(compra);
            // Aquí puedes agregar lógica para validar si el asiento ya está ocupado
        }

        // Calcular el monto total
        double montoTotal = boletos.size() * funcion.getPrecioBoleto();
        compra.setMonto(montoTotal);

        // Guardar la compra y los boletos (gracias a CascadeType.ALL)
        Compra compraGuardada = compraRepository.save(compra);

        // Actualizar dinero recaudado en la función
        funcion.setDineroRecaudado(funcion.getDineroRecaudado() + montoTotal);

        // No es necesario agregar los boletos a la lista en la función, JPA lo hace automáticamente
        funcionRepository.save(funcion);

        return ResponseEntity.status(HttpStatus.CREATED).body(compraGuardada);
    }
    @CrossOrigin
    @DeleteMapping("/cancelar-compra/{idCompra}")
    @Transactional
    public ResponseEntity<?> cancelarCompra(@PathVariable Integer idCompra) {
        Optional<Compra> compraOptional = compraRepository.findById(idCompra);
        if (!compraOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La compra no existe.");
        }

        Compra compra = compraOptional.get();
        Funcion funcion = compra.getFuncion();

        // Actualizar el dinero recaudado en la función
        funcion.setDineroRecaudado(funcion.getDineroRecaudado() - compra.getMonto());

        // Actualizar la lista de boletos vendidos en la función
        List<Boleto> boletosCompra = compra.getBoletos();
        funcion.getBoletosVendidos().removeAll(boletosCompra);

        // Guardar los cambios en la función
        funcionRepository.save(funcion);

        // Eliminar la compra (los boletos asociados se eliminarán gracias a CascadeType.ALL)
        compraRepository.delete(compra);

        return ResponseEntity.ok("La compra ha sido cancelada exitosamente.");
    }

}
