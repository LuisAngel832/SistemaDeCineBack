package com.example.metrix.service;



import com.example.metrix.DTO.CompraDTO;
import com.example.metrix.model.Compra;

public interface CompraService {
    Compra registrarCompra(CompraDTO compra);
    Compra cancelarCompra(Integer idCompra);
}
