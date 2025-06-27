package com.example.metrix.controller;

import com.example.metrix.model.Compra;
import com.example.metrix.service.CompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
@RestController
@RequestMapping("api/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @CrossOrigin
    @PostMapping
    @Transactional
    public ResponseEntity<?> registrarCompra(@RequestBody Compra compra) {
        try{
            Compra compraGuardada = compraService.registrarCompra(compra);
            return ResponseEntity.ok(compraGuardada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @CrossOrigin
    @DeleteMapping("/{idCompra}")
    @Transactional
    public ResponseEntity<?> cancelarCompra(@PathVariable Integer idCompra) {
        try{
            Compra compra = compraService.cancelarCompra(idCompra);
            return ResponseEntity.ok(compra);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
        

}
