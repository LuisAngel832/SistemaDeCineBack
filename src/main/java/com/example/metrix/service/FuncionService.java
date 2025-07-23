package com.example.metrix.service;

import com.example.metrix.DTO.FuncionConPeliculaDTO;
import com.example.metrix.DTO.FuncionDTO;
import com.example.metrix.model.Funcion;

import java.time.LocalDate;
import java.util.List;

public interface FuncionService {
    List<Funcion> obtenerTodasLasFunciones();
    Funcion obtenerFuncionById(Integer id);
    Funcion registrarFuncion( FuncionConPeliculaDTO funcionDTO);
    Funcion asociarFuncionAPelicula(Funcion funcion, Integer idPelicula);
    Boolean eliminarFuncion(Integer id);
    Funcion actualizarFuncion(Integer id, FuncionDTO nuevaFuncion);
    Boolean eliminarTodasLasFunciones();
    List<Funcion> findByPeliculaTituloContainingIgnoreCase(String titulo);
    List<Funcion> buscarfuncionesPorFecha(LocalDate fecha);
}
