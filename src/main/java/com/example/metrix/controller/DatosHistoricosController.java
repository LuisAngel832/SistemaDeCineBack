
package com.example.metrix.controller;

import com.example.metrix.model.DatosHistoricos;
import com.example.metrix.repository.DatosHistoricosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DatosHistoricosController {

    @Autowired
    DatosHistoricosRepository datosHistoricosRepository;

    @CrossOrigin
    @GetMapping
    public DatosHistoricos obtenerDatosHistoricos() {
        Optional<DatosHistoricos> datos = datosHistoricosRepository.findById(1);
        return datos.orElse(null);
    }
}