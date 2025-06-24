package com.example.metrix.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra;

    @NotNull(message = "EL MONTO NO PUEDE SER NULO")
    private double monto;

    @NotNull(message = "EL ID FUNCION NO PUEDE SER NULO")
    @ManyToOne
    private Funcion funcion;

    @NotNull
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compra", orphanRemoval = true)
    private List<Boleto> boletos = new ArrayList<>();

    public @NotNull List<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(@NotNull List<Boleto> boletos) {
        this.boletos = boletos;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    @NotNull(message = "EL MONTO NO PUEDE SER NULO")
    public double getMonto() {
        return monto;
    }

    public void setMonto(@NotNull(message = "EL MONTO NO PUEDE SER NULO") double monto) {
        this.monto = monto;
    }

    public @NotNull(message = "EL ID FUNCION NO PUEDE SER NULO") Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(@NotNull(message = "EL ID FUNCION NO PUEDE SER NULO") Funcion funcion) {
        this.funcion = funcion;
    }

    public @NotNull  List<Boleto> getBoleto() {
        return boletos;
    }

    public void setBoleto(@NotNull  List<Boleto> boletos) {
        this.boletos = boletos;
    }
}
