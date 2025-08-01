package com.example.metrix.repository;

import com.example.metrix.model.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Integer> {

   Optional<Funcion> findByFechaAndHora(LocalDate fecha, LocalTime hora);
   List<Funcion> findByFechaBeforeAndEstado(LocalDate fecha, String estado);

   List<Funcion> findByFechaBetweenAndEstado(LocalDate startDate, LocalDate endDate, String estado);
   List<Funcion> findByEstado(String estado);

   List<Funcion> findByPeliculaTituloContainingIgnoreCase(String titulo);
   List<Funcion> findByFecha(LocalDate fecha);

}
