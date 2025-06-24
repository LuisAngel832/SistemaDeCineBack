package com.example.metrix.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private int fila;

    @NotNull
    private int coluna;

    @ManyToOne
    @JoinColumn(name = "funcion_id")
    @JsonBackReference(value = "funcion-boletos")
    private Funcion funcion;

    @NotNull
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "compra_id")
    private Compra compra;

    public @NotNull Compra getCompra() {
        return compra;
    }

    public void setCompra(@NotNull Compra compra) {
        this.compra = compra;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    public int getFila() {
        return fila;
    }

    public void setFila(@NotNull int fila) {
        this.fila = fila;
    }

    @NotNull
    public int getColuna() {
        return coluna;
    }

    public void setColuna(@NotNull int coluna) {
        this.coluna = coluna;
    }
}
