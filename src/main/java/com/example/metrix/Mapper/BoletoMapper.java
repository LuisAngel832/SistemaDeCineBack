/*package com.example.metrix.Mapper;

import com.example.metrix.model.Boleto;
import com.example.metrix.model.Compra;
import com.example.metrix.model.Funcion;
import com.example.metrix.repository.CompraRepository;

import java.util.ArrayList;
import java.util.List;

public class BoletoMapper {

    // Se inyecta en tiempo de ejecución, necesitas setearlo antes de usar mapAsientosAObjetos
    private static CompraRepository compraRepository;

    public static void setCompraRepository(CompraRepository repository) {
        compraRepository = repository;
    }

    public static List<Boleto> mapAsientosAObjetos(List<String> nombresAsientos, Funcion funcion) {
        if (compraRepository == null) {
            throw new IllegalStateException("CompraRepository no ha sido seteado en BoletoMapper");
        }

        // Crear y guardar compra dummy
        Compra compraDummy = new Compra();
        compraDummy.setFuncion(funcion);
        compraDummy = compraRepository.save(compraDummy);

        List<Boleto> boletos = new ArrayList<>();

        for (String nombre : nombresAsientos) {
            int fila = Integer.parseInt(nombre.substring(0, 1));
            char letra = nombre.charAt(1);
            int columna = letra - 'A';

            Boleto boleto = new Boleto();
            boleto.setFila(fila);
            boleto.setColuna(columna);
            boleto.setFuncion(funcion);
            boleto.setCompra(compraDummy); // Seteamos la compra dummy

            boletos.add(boleto);
        }

        return boletos;
    }
}*/
