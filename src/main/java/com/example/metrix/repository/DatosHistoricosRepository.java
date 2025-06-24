package com.example.metrix.repository;

import com.example.metrix.model.DatosHistoricos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosHistoricosRepository extends JpaRepository<DatosHistoricos, Integer> {

}
