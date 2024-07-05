package com.example.tiendaElectronica.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(name = "detalle_pedido")

public class DetallePedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="pedido_id")
    @JsonBackReference
    private PedidoEntity pedidoEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="producto_id")
    private ProductoEntity productoEntity;
    private int cantidad;

    public DetallePedidoEntity() {
    }

    public DetallePedidoEntity(Long id, PedidoEntity pedidoEntity, ProductoEntity productoEntity, int cantidad) {
        this.id = id;
        this.pedidoEntity = pedidoEntity;
        this.productoEntity = productoEntity;
        this.cantidad = cantidad;
    }

    public Double getSubTotal() {
        Double precio=productoEntity.getPrecio();
        return precio*cantidad;
    }
    public void actualizarStock(){

        if(productoEntity!=null){
            int stockActualizado= productoEntity.getStock()-cantidad;
            if(stockActualizado<0){
                throw new RuntimeException("No hay suficiente stock para el producto "+productoEntity.getNombre());
            }
            productoEntity.setStock(stockActualizado);
        }else {
            throw new IllegalStateException("El producto asociado al detalle del pedido es nulo.");
        }


    }

}
