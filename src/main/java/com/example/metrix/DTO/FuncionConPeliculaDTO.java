package com.example.metrix.DTO;

import com.example.metrix.model.Funcion;
import com.example.metrix.model.Pelicula;

import jakarta.validation.constraints.NotNull;

public class FuncionConPeliculaDTO {
    @NotNull
    private Funcion funcion;
    @NotNull
    private Pelicula pelicula;

    // Getters y Setters
    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
