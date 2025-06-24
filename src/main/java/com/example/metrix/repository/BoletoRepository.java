package com.example.metrix.repository;

import com.example.metrix.model.Boleto;
import com.example.metrix.model.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoletoRepository extends JpaRepository<Boleto,Integer> {
    List<Boleto> findByFuncion(Funcion funcion);

}
