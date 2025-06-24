package com.example.metrix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class    Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El titulo no puede ser nulo")
    private String titulo;

    @NotNull(message = "La duracion no puede ser nulo")
    private String duracion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "El titulo no puede ser nulo") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotNull(message = "El titulo no puede ser nulo") String titulo) {
        this.titulo = titulo;
    }

    public @NotNull(message = "La duracion no puede ser nulo") String getDuracion() {
        return duracion;
    }

    public void setDuracion(@NotNull(message = "La duracion no puede ser nulo") String duracion) {
        this.duracion = duracion;
    }
}
