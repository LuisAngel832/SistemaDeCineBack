package com.example.metrix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.metrix.model.Boleto;
import com.example.metrix.model.Compra;
import com.example.metrix.model.Funcion;
import com.example.metrix.repository.BoletoRepository;
import com.example.metrix.repository.CompraRepository;
import com.example.metrix.repository.FuncionRepository;
@Service
public class BoletosServiceImpl implements BoletoService {
    
    private BoletoRepository boletoRepository;

    private CompraRepository compraRepository;
    private FuncionRepository funcionRepository;

    public BoletosServiceImpl(BoletoRepository boletoRepository, CompraRepository compraRepository, FuncionRepository funcionRepository) {
        this.boletoRepository = boletoRepository;
        this.compraRepository = compraRepository;
        this.funcionRepository = funcionRepository;
    }

    @Override
    public boolean cancelarBoleto(Integer idBoleto) {
        Optional<Boleto> boletoOptional = boletoRepository.findById(idBoleto);
        if (!boletoOptional.isPresent()) {
            throw new IllegalArgumentException("El boleto no existe.");
        }

        Boleto boleto = boletoOptional.get();
        Compra compra = boleto.getCompra();
        Funcion funcion = boleto.getFuncion();

        double precioBoleto = funcion.getPrecioBoleto();
        funcion.setDineroRecaudado(funcion.getDineroRecaudado() - precioBoleto);

        funcion.getBoletosVendidos().remove(boleto);
        funcionRepository.save(funcion);

        compra.getBoletos().remove(boleto);
        compra.setMonto(compra.getMonto() - precioBoleto);

        if (compra.getBoletos().isEmpty()) {
            compraRepository.delete(compra);
        } else {
            compraRepository.save(compra);
        }

        boletoRepository.delete(boleto);

        return true;
    
    }


    @Override
    public Integer obtenerCantidadDeBoletosVendidos(Integer idFuncion) {
        Optional<Funcion> funcionOptional = funcionRepository.findById(idFuncion);
        if (!funcionOptional.isPresent()) {
            throw new IllegalArgumentException("La función no existe.");
        }

        Funcion funcion = funcionOptional.get();
        return funcion.getBoletosVendidos().size();
        
    }

    @Override 
    public List<Boleto> obtenerBoletosVendidos(Integer idFuncion){
        Optional<Funcion> funcionOptional = funcionRepository.findById(idFuncion);
        if (!funcionOptional.isPresent()) {
            throw new IllegalArgumentException("La función no existe.");
        }

        Funcion funcion = funcionOptional.get();
        return funcion.getBoletosVendidos();
    }
}
