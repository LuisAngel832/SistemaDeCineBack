package com.example.metrix.repository;

import com.example.metrix.model.Boleto;
import com.example.metrix.model.Compra;
import com.example.metrix.model.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {
    List<Compra> findByFuncion(Funcion funcion);

}
