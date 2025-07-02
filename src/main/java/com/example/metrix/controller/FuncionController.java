package com.example.metrix.controller;

import com.example.metrix.DTO.FuncionConPeliculaDTO;
import com.example.metrix.model.Funcion;
import com.example.metrix.service.FuncionService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/funciones")
public class FuncionController {
    @Autowired
    private FuncionService funcionService;

    public FuncionController(FuncionService funcionService) {
        this.funcionService = funcionService;
    }

    @CrossOrigin
    @GetMapping
    public List<Funcion> retornarTodasFunciones() {
        return funcionService.obtenerTodasLasFunciones();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Funcion> detallesFuncion(@PathVariable Integer id) {
        try {
            Funcion funcion = funcionService.obtenerFuncionById(id);
            return ResponseEntity.ok(funcion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> registrarFuncion(@Valid @RequestBody FuncionConPeliculaDTO funcionDTO) {
        try {
            Funcion funcion = funcionService.registrarFuncion(funcionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(funcion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin
    @PostMapping("/asociar")
    public ResponseEntity<?> registrarFuncion(@RequestBody Funcion funcion, @RequestParam Integer idPelicula) {
        try {
            Funcion nuevaFuncion = funcionService.asociarFuncionAPelicula(funcion, idPelicula);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFuncion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin
    @DeleteMapping
    public ResponseEntity<?> borrarFuncion(@PathVariable Integer id) {
        try {
            funcionService.eliminarFuncion(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actulizarFuncion(@PathVariable Integer id, Funcion funcion) {
        try {
            Funcion nuevaFuncion = funcionService.actualizarFuncion(id, funcion);
            return ResponseEntity.ok(nuevaFuncion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @CrossOrigin
    @DeleteMapping("borrarTodasFunciones")
    public ResponseEntity<Void> borrarTodasFunciones() {
        try {
            funcionService.eliminarTodasLasFunciones();
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @CrossOrigin
    @GetMapping("/buscar")
    public ResponseEntity<List<Funcion>> buscarFunciones(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<Funcion> funciones = null;

        if (titulo != null) {
            System.out.println("entro a buscar por titulo");
            funciones = funcionService.findByPeliculaTituloContainingIgnoreCase(titulo);
        } else if (fecha != null) {
            System.out.println("entro a buscar por fecha");
            funciones = funcionService.buscarfuncionesPorFecha(fecha);
        } else {
            System.out.println("entro a obtener todas las funciones");
            funciones = funcionService.obtenerTodasLasFunciones();
        }

        return ResponseEntity.ok(funciones);
    }

}