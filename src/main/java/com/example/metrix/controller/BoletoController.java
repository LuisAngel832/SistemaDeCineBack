package com.example.metrix.controller;

import com.example.metrix.model.Boleto;
import com.example.metrix.service.BoletoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("api/boletos")
public class BoletoController {

    private BoletoService boletoService;

    @Autowired
    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @CrossOrigin
    @DeleteMapping("/{idBoleto}")
    @Transactional
    public ResponseEntity<?> cancelarBoleto(@PathVariable Integer idBoleto) {
        try {
            boolean boletoCancelado = boletoService.cancelarBoleto(idBoleto);
            return ResponseEntity.ok(boletoCancelado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping("/cantidad/{id}")
    public ResponseEntity<?> obtenerCantidadBoletosVendidos(@PathVariable Integer id) {
        try {
            int cantidadBoletos = boletoService.obtenerCantidadDeBoletosVendidos(id);
            return ResponseEntity.ok(cantidadBoletos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping("/{idFuncion}")
    public ResponseEntity<?> obtenerBoletosVendidos(@PathVariable Integer id) {
        try {
            List<Boleto> boletos = boletoService.obtenerBoletosVendidos(id);
            return ResponseEntity.ok(boletos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
