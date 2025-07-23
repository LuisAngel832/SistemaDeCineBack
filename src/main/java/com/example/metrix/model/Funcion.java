package com.example.metrix.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    @NotNull(message = "La película no puede ser nula")
    private Pelicula pelicula;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;

    @NotNull(message = "La hora no puede ser nula")
    private LocalTime hora; 

    @NotNull(message = "El precio del boleto no puede ser nulo")
    @Column(name = "precio_boleto")
    private double precioBoleto;

    @NotNull(message = "El estado no puede ser nulo")
    private String estado;

    @NotNull(message = "Los asientos totales no pueden ser nulos")
    @Column(name = "asientos_totales")
    private Integer asientosTotales;

    @Column(name = "dinero_recaudado")
    private Double dineroRecaudado = 0.0;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcion")
    @JsonManagedReference(value = "funcion-boletos")
    private List<Boleto> boletosVendidos = new ArrayList<>();

   
    public Funcion() {}

    public Funcion(Integer id, Pelicula pelicula, LocalDate fecha, LocalTime hora,
                   double precioBoleto, String estado, Integer asientosTotales) {
        this.id = id;
        this.pelicula = pelicula;
        this.fecha = fecha;
        this.hora = hora;
        this.precioBoleto = precioBoleto;
        this.estado = estado;
        this.asientosTotales = asientosTotales;
        this.dineroRecaudado = 0.0;
        this.boletosVendidos = new ArrayList<>();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Pelicula getPelicula() { return pelicula; }
    public void setPelicula(Pelicula pelicula) { this.pelicula = pelicula; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public double getPrecioBoleto() { return precioBoleto; }
    public void setPrecioBoleto(double precioBoleto) { this.precioBoleto = precioBoleto; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getAsientosTotales() { return asientosTotales; }
    public void setAsientosTotales(Integer asientosTotales) { this.asientosTotales = asientosTotales; }

    public Double getDineroRecaudado() { return dineroRecaudado; }
    public void setDineroRecaudado(Double dineroRecaudado) { this.dineroRecaudado = dineroRecaudado; }

    public List<Boleto> getBoletosVendidos() { return boletosVendidos; }
    public void setBoletosVendidos(List<Boleto> boletosVendidos) { this.boletosVendidos = boletosVendidos; }

    public String toString() {
        return "Funcion{" +
                "id=" + id +
                ", pelicula=" + pelicula +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", precioBoleto=" + precioBoleto +
                ", estado='" + estado + '\'' +
                ", asientosTotales=" + asientosTotales +
                ", dineroRecaudado=" + dineroRecaudado +
                ", boletosVendidos=" + boletosVendidos +
                '}';
    }
}
