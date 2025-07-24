// src/main/java/com/tuapp/controller/PeliculaController.java

package com.example.metrix.controller;

import com.example.metrix.model.Pelicula;
import com.example.metrix.service.PeliculaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @PostMapping
    public Pelicula crear(@RequestBody Pelicula pelicula) {
        return peliculaService.guardarPelicula(pelicula);
    }

    @GetMapping
    public List<Pelicula> listar() {
        return peliculaService.retornarTodasPeluclas();
    }

    @PutMapping("/{id}")
    public Pelicula actualizar(@PathVariable Integer id, @RequestBody Pelicula pelicula) {
        return peliculaService.actualizarPelicula(id, pelicula);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        peliculaService.eliminarPelicula(id);
    }
}
