package com.example.metrix.service;
import com.example.metrix.model.Pelicula;
import java.util.List;

public interface PeliculaService {
    List<Pelicula> retornarTodasPeluclas();
    Pelicula guardarPelicula(Pelicula pelicula);
    Pelicula actualizarPelicula(Integer id,Pelicula pelicula);
    void eliminarPelicula(Integer id);
    Pelicula buscarPeliculaPorId(Integer id);
    
}
