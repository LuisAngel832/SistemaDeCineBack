package com.example.metrix.service;

import com.example.metrix.model.Pelicula;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.metrix.repository.PeliculaRepository;

@Service
public class PeliculaServiceImpl implements PeliculaService{
    
    private PeliculaRepository peliculaRepository;

    public PeliculaServiceImpl(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    @Override
    public List<Pelicula> retornarTodasPeluclas() {
        return peliculaRepository.findAll();
    }

    @Override
    public Pelicula guardarPelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Override
   public Pelicula actualizarPelicula(Integer id, Pelicula nuevaPelicula) {
        return peliculaRepository.findById(id)
            .map(pelicula -> {
                pelicula.setTitulo(nuevaPelicula.getTitulo());
                pelicula.setGenero(nuevaPelicula.getGenero());
                pelicula.setDescripcion(nuevaPelicula.getDescripcion());
                pelicula.setDuracion(nuevaPelicula.getDuracion());
                return peliculaRepository.save(pelicula);
            })
            .orElseThrow(() -> new RuntimeException("Película no encontrada"));
    }

    @Override
    public void eliminarPelicula(Integer id) {
        peliculaRepository.deleteById(id);
    }

    @Override
    public Pelicula buscarPeliculaPorId(Integer id) {
        return peliculaRepository.findById(id).orElse(null);
    }
}
