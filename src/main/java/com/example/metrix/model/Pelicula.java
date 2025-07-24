// src/main/java/com/tuapp/model/Pelicula.java

package com.example.metrix.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    private String genero;
    private String descripcion;

    @Column(nullable = false)
    private int duracion; // en minutos
}
