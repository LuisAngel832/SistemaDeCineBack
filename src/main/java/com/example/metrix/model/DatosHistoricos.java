package com.example.metrix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class  DatosHistoricos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idDatos;

    @NotNull(message = "")
    private double totalDeVentas;
    private int totalBoletosOfertados;
    private int totalAsientosOcupados;
    private LocalDate fechaInicio = LocalDate.of(2024,10,22);
    private int numeroDeFuncionesImpartidas;

    public Integer getIdDatos() {
        return idDatos;
    }

    public void setIdDatos(Integer idDatos) {
        this.idDatos = idDatos;
    }

    @NotNull(message = "")
    public double getTotalDeVentas() {
        return totalDeVentas;
    }

    public void setTotalDeVentas(@NotNull(message = "") double totalDeVentas) {
        this.totalDeVentas = totalDeVentas;
    }

    public int getTotalBoletosOfertados() {
        return totalBoletosOfertados;
    }

    public void setTotalBoletosOfertados(int totalBoletosOfertados) {
        this.totalBoletosOfertados = totalBoletosOfertados;
    }

    public int getTotalAsientosOcupados() {
        return totalAsientosOcupados;
    }

    public void setTotalAsientosOcupados(int totalAsientosOcupados) {
        this.totalAsientosOcupados = totalAsientosOcupados;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getNumeroDeFuncionesImpartidas() {
        return numeroDeFuncionesImpartidas;
    }

    public void setNumeroDeFuncionesImpartidas(int numeroDeFuncionesImpartidas) {
        this.numeroDeFuncionesImpartidas = numeroDeFuncionesImpartidas;
    }
}
