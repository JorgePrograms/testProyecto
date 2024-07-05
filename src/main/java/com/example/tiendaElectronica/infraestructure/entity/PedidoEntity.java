package com.example.tiendaElectronica.infraestructure.entity;

import com.example.tiendaElectronica.domain.model.DetallePedido;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montoTotal=0.0;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private UsuarioEntity usuarioEntity;
    @ManyToOne
    @JoinColumn(name="metodo_pago_id")
    @JsonBackReference
    private MetodoPagoEntity metodoPagoEntity;
    @OneToMany(mappedBy = "pedidoEntity")
    @JsonManagedReference
    private List<DetallePedidoEntity>detallePedidoEntities=new ArrayList<>();

    public PedidoEntity() {
    }

    public PedidoEntity(Long id, Double montoTotal, UsuarioEntity usuarioEntity, MetodoPagoEntity metodoPagoEntity) {
        this.id = id;
        this.montoTotal = montoTotal;
        this.usuarioEntity = usuarioEntity;
        this.metodoPagoEntity = metodoPagoEntity;
    }

    public PedidoEntity(Long id, Double montoTotal, UsuarioEntity usuarioEntity, MetodoPagoEntity metodoPagoEntity, List<DetallePedidoEntity> detallePedidoEntities) {
        this.id = id;
        this.montoTotal = montoTotal;
        this.usuarioEntity = usuarioEntity;
        this.metodoPagoEntity = metodoPagoEntity;
        this.detallePedidoEntities = detallePedidoEntities;
    }

    public void calcularMontoTotal(){
        Double total=0.0;
        for(DetallePedidoEntity detalle:detallePedidoEntities){
            total = detalle.getSubTotal();
        }
        this.montoTotal=total;
    }



}