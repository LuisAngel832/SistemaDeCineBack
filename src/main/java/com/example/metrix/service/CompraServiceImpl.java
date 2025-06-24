package com.example.metrix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public Compra registrarCompra(Compra compra) {
        Optional<Funcion> funcionOptional = funcionRepository.findById(compra.getFuncion().getId());
        if (!funcionOptional.isPresent()) {
           throw new IllegalArgumentException("La función no existe.");
        }

        Funcion funcion = funcionOptional.get();

        List<Boleto> boletos = compra.getBoletos();
        for (Boleto boleto : boletos) {
            boleto.setFuncion(funcion);
            boleto.setCompra(compra);
        }

        double montoTotal = boletos.size() * funcion.getPrecioBoleto();
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
