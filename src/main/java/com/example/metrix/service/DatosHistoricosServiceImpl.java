package com.example.metrix.service;

import com.example.metrix.model.DatosHistoricos;
import com.example.metrix.repository.DatosHistoricosRepository;
import java.lang.IllegalArgumentException;
import org.springframework.stereotype.Service;

@Service
public class DatosHistoricosServiceImpl implements DatosHistoricosService {
    private final DatosHistoricosRepository datosHistoricosRepository;

    public DatosHistoricosServiceImpl(DatosHistoricosRepository datosHistoricosRepository) {
        this.datosHistoricosRepository = datosHistoricosRepository;
    }

    
    @Override
    public DatosHistoricos obtenerDatosHistoricos(Integer idFuncion) {
        return datosHistoricosRepository.findById(idFuncion).orElseThrow(()-> new IllegalArgumentException("No se encontraron datos"));
    }
    
}
