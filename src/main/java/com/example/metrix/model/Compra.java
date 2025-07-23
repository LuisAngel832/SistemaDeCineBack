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
    
    public Compra() {}

    public void  setMonto (double monto) {
        this.monto = monto;
    }

    public void setFuncion (Funcion funcion) {
        this.funcion = funcion;
    }

    public void setBoletos (List<Boleto> boletos) {
        this.boletos = boletos;
    }

    public double getMonto () {
        return monto;
    }

    public Funcion getFuncion () {
        return funcion;
    }

    public List<Boleto> getBoletos () {
        return boletos;
    }
    


}