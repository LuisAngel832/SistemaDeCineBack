package com.example.metrix.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.metrix.DTO.CompraDTO;
import com.example.metrix.model.Boleto;
import com.example.metrix.model.Compra;
import com.example.metrix.model.Funcion;
import com.example.metrix.repository.CompraRepository;
import com.example.metrix.repository.FuncionRepository;
import java.lang.IllegalArgumentException;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;
    private final FuncionRepository funcionRepository;

    public CompraServiceImpl(CompraRepository compraRepository, FuncionRepository funcionRepository) {
        this.compraRepository = compraRepository;
        this.funcionRepository = funcionRepository;
    }

    @Override
    public Compra registrarCompra(CompraDTO dto) {
        Funcion funcion = funcionRepository.findById(dto.getFuncionId())
                .orElseThrow(() -> new IllegalArgumentException("La función no existe."));

        Compra compra = new Compra();
        compra.setFuncion(funcion);

        // Lista nueva para los boletos de esta compra
        List<Boleto> nuevosBoletos = new ArrayList<>();

        // Verificar boletos ya vendidos
        List<Boleto> boletosVendidos = funcion.getBoletosVendidos();

        for (String codigo : dto.getBoletos()) {
            if (boletosVendidos.stream().anyMatch(b -> b.getCodigoAsiento().equals(codigo))) {
                throw new IllegalArgumentException("El boleto ya fue vendido: " + codigo);
            }

            Boleto boleto = new Boleto();
            boleto.setCodigoAsiento(codigo);
            boleto.setFuncion(funcion);
            boleto.setCompra(compra);

            nuevosBoletos.add(boleto);
        }

        compra.setBoletos(nuevosBoletos); 
        boletosVendidos.addAll(nuevosBoletos); 

        double montoTotal = nuevosBoletos.size() * funcion.getPrecioBoleto();
        compra.setMonto(montoTotal);

        Compra compraGuardada = compraRepository.save(compra);

        funcion.setDineroRecaudado(funcion.getDineroRecaudado() + montoTotal);
        funcionRepository.save(funcion);

        return compraGuardada;
    }

    @Override
    public Compra cancelarCompra(Integer idCompra) {
        Optional<Compra> compraOptional = compraRepository.findById(idCompra);
        if (!compraOptional.isPresent()) {
            throw new IllegalArgumentException("La compra no existe.");
        }

        Compra compra = compraOptional.get();
        Funcion funcion = compra.getFuncion();

        funcion.setDineroRecaudado(funcion.getDineroRecaudado() - compra.getMonto());

        List<Boleto> boletosCompra = compra.getBoletos();
        funcion.getBoletosVendidos().removeAll(boletosCompra);

        funcionRepository.save(funcion);

        compraRepository.delete(compra);

        return compra;
    }
}
