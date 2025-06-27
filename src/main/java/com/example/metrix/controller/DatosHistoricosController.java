
package com.example.metrix.controller;

import com.example.metrix.model.DatosHistoricos;
import com.example.metrix.repository.DatosHistoricosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.metrix.service.DatosHistoricosService;


@RestController
@RequestMapping("/api/historicos")
@CrossOrigin
public class DatosHistoricosController {
    private  DatosHistoricosService datosService;

    @Autowired
    public DatosHistoricosController(DatosHistoricosService datosService) {
        this.datosService = datosService;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDatosHistoricos(@PathVariable Integer id) {
        try{
            DatosHistoricos datosHistoricos = datosService.obtenerDatosHistoricos(id);
            return ResponseEntity.ok(datosHistoricos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}