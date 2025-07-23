package com.example.metrix.DTO;

import java.util.List;

public class FuncionDTO {
    private Long id;
    private int peliculaId; 
    private String fecha;
    private String hora;
    private Double precioBoleto;
    private String estado;
    private Integer asientosTotales;
    private Double dineroRecaudado;
    private List<String> boletosVendidos; // Solo los IDs o nombres de los asientos

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(int peliculaId) {
        this.peliculaId = peliculaId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Double getPrecioBoleto() {
        return precioBoleto;
    }

    public void setPrecioBoleto(Double precioBoleto) {
        this.precioBoleto = precioBoleto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getAsientosTotales() {
        return asientosTotales;
    }

    public void setAsientosTotales(Integer asientosTotales) {
        this.asientosTotales = asientosTotales;
    }

    public Double getDineroRecaudado() {
        return dineroRecaudado;
    }

    public void setDineroRecaudado(Double dineroRecaudado) {
        this.dineroRecaudado = dineroRecaudado;
    }

    public List<String> getBoletosVendidos() {
        return boletosVendidos;
    }

    public void setBoletosVendidos(List<String> boletosVendidos) {
        this.boletosVendidos = boletosVendidos;
    }
}
