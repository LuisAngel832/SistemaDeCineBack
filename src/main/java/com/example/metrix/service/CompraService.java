package com.example.metrix.service;



import com.example.metrix.model.Compra;

public interface CompraService {
    Compra registrarCompra(Compra compra);
    Compra cancelarCompra(Integer idCompra);
}
