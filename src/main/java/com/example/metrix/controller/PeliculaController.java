package com.example.metrix.controller;

import com.example.metrix.model.Pelicula;
import com.example.metrix.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @CrossOrigin
    @GetMapping("/todas-peliculas")
    public List<Pelicula> retornarTodasPeluclas() {
        return peliculaRepository.findAll();
    }
}
