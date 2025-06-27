package com.example.metrix.service;
import com.example.metrix.model.Boleto;
import java.util.List;

public interface BoletoService {
    boolean cancelarBoleto(Integer idBoleto);
    Integer obtenerCantidadDeBoletosVendidos(Integer idFuncion);
    List<Boleto> obtenerBoletosVendidos(Integer idFuncion);
}
