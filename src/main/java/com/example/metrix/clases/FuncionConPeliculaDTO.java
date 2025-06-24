package com.example.metrix.clases;

import com.example.metrix.model.Funcion;
import com.example.metrix.model.Pelicula;

public class FuncionConPeliculaDTO {
    private Funcion funcion;
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
