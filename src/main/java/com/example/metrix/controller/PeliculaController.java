package com.example.metrix.controller;

import com.example.metrix.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.metrix.service.PeliculaService;

import java.util.List;

@RestController
@RequestMapping("api/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @CrossOrigin
    @GetMapping("/todas-peliculas")
    public List<Pelicula> retornarTodasPeluclas() {
        return peliculaService.retornarTodasPeluclas();
    }
}
