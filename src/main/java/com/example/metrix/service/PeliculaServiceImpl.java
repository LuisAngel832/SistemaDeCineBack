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
}
