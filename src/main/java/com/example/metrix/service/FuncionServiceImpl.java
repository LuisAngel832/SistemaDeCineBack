package com.example.metrix.service;

import com.example.metrix.model.Boleto;
import com.example.metrix.model.Funcion;
import com.example.metrix.model.Pelicula;
import com.example.metrix.repository.CompraRepository;
import com.example.metrix.repository.FuncionRepository;
import com.example.metrix.repository.PeliculaRepository;

import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.metrix.DTO.FuncionConPeliculaDTO;
import com.example.metrix.DTO.FuncionDTO;

import java.lang.IllegalArgumentException;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class FuncionServiceImpl implements FuncionService {

    @Autowired
    private CompraRepository compraRepository;

    

    private FuncionRepository funcionRepository;
    private PeliculaRepository peliculaRepository;

    public FuncionServiceImpl(FuncionRepository funcionRepository, PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
        this.funcionRepository = funcionRepository;
    }

    @Override
    public List<Funcion> obtenerTodasLasFunciones() {
        List<Funcion> funciones = funcionRepository.findAll();
        if (funciones.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron funciones");
        }
        return funciones;
    }

    @Override
    public Funcion obtenerFuncionById(Integer idFuncion) {
        return funcionRepository.findById(idFuncion)
                .orElseThrow(() -> new IllegalArgumentException("No se encontraron funciones"));
    }

    @Override
    public Funcion registrarFuncion(@RequestBody FuncionConPeliculaDTO funcionDTO) {
        Pelicula pelicula = funcionDTO.getPelicula();
        Funcion funcion = funcionDTO.getFuncion();
        Optional<Funcion> funcionExistente = funcionRepository.findByFechaAndHora(funcion.getFecha(),
                LocalTime.of(funcion.getHora().getHour(), funcion.getHora().getMinute()));
        if (funcionExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe una función programada en esa fecha y hora.");
        }
        Pelicula peliculaGuardada = peliculaRepository.save(pelicula);

        funcion.setPelicula(peliculaGuardada);

        return funcionRepository.save(funcion);

    }

    @Override
    public Funcion asociarFuncionAPelicula(@RequestBody Funcion funcion, Integer idPelicula) {
        Optional<Pelicula> peliculaOptional = peliculaRepository.findById(idPelicula);
        if (!peliculaOptional.isPresent()) {
            throw new IllegalArgumentException("La pelicula no existe.");
        }
        Optional<Funcion> funcionExistente = funcionRepository.findByFechaAndHora(funcion.getFecha(),
                LocalTime.of(funcion.getHora().getHour(), funcion.getHora().getMinute()));
        if (funcionExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe una función programada en esa fecha y hora.");
        }

        funcion.setPelicula(peliculaOptional.get());

        return funcionRepository.save(funcion);
    }

    @Override
    public Boolean eliminarFuncion(Integer id) {
        Optional<Funcion> funcionOptional = funcionRepository.findById(id);
        if (!funcionOptional.isPresent()) {
            throw new IllegalArgumentException("La función no existe.");
        }

        if (!funcionOptional.get().getBoletosVendidos().isEmpty()) {
            throw new IllegalArgumentException("No se puede eliminar la función porque ya tiene boletos vendidos.");
        }

        funcionRepository.deleteById(id);
        return true;
    }

    @Override
    public Funcion actualizarFuncion(Integer id, FuncionDTO dto) {
        // 1. Buscar función por ID
        Funcion funcion = funcionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La función no existe"));

        // 2. Buscar película asociada
        Pelicula pelicula = peliculaRepository.findById(dto.getPeliculaId())
                .orElseThrow(() -> new IllegalArgumentException("Película no encontrada"));


        // 4. Asignar campos
        funcion.setPelicula(pelicula);
        funcion.setFecha(LocalDate.parse(dto.getFecha()));
        funcion.setHora(LocalTime.parse(dto.getHora()));
        funcion.setPrecioBoleto(dto.getPrecioBoleto());
        funcion.setEstado(dto.getEstado());
        funcion.setAsientosTotales(dto.getAsientosTotales());
        funcion.setDineroRecaudado(dto.getDineroRecaudado());

        // 5. Guardar
        return funcionRepository.save(funcion);
    }

    @Override
    public Boolean eliminarTodasLasFunciones() {
        funcionRepository.deleteAll();
        return true;
    }

    @Override
    public List<Funcion> findByPeliculaTituloContainingIgnoreCase(String titulo) {

        return funcionRepository.findByPeliculaTituloContainingIgnoreCase(titulo);
    }

    @Override
    public List<Funcion> buscarfuncionesPorFecha(LocalDate fecha) {
        return funcionRepository.findByFecha(fecha);
    }

}
