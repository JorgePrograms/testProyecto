package com.example.tiendaElectronica.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class MetodoPago {


    private Long id;
    private Date fechaVencimiento;
    private Integer cvv;
    private String cuentaBancaria;
    private String tipo;
    private List<Pedido> pedidos;

    public MetodoPago() {
    }

    public MetodoPago(Long id, Date fechaVencimiento, Integer cvv, String cuentaBancaria, String tipo) {
        this.id = id;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
        this.cuentaBancaria = cuentaBancaria;
        this.tipo = tipo;
    }

    public MetodoPago(Long id, Date fechaVencimiento, int cvv, String cuentaBancaria, String tipo, List<Pedido> pedidos) {
        this.id = id;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
        this.cuentaBancaria = cuentaBancaria;
        this.tipo = tipo;
        this.pedidos = pedidos;
    }
}
