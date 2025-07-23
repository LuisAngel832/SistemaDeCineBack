package com.example.metrix.DTO;

import java.util.List;

import com.example.metrix.model.Boleto;

public class CompraDTO {
    private double monto;
    private Integer funcionId;
    private List<String> boletos;

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Integer getFuncionId() {
        return funcionId;
    }

    public void setFuncion(Integer funcionId) {
        this.funcionId = funcionId;
    }

    public List<String> getBoletos() {
        return boletos;
    }

    public void setBoletos(List<String> boletos) {
        this.boletos = boletos;
    }
}