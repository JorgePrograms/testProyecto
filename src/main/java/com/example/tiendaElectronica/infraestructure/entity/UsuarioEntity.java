package com.example.tiendaElectronica.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name="usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreUsuario;
    private String contrasenia;
    private String correoElectronico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteEntity;
    @OneToMany(mappedBy = "usuarioEntity",cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<PedidoEntity> pedidosEntities=new ArrayList<>();

    public UsuarioEntity() {
    }

    public UsuarioEntity(Long id, String nombreUsuario, String contrasenia, String correoElectronico, ClienteEntity clienteEntity) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.correoElectronico = correoElectronico;
        this.clienteEntity = clienteEntity;
    }

    public UsuarioEntity(Long id, String nombreUsuario, String contrasenia, String correoElectronico, ClienteEntity clienteEntity, List<PedidoEntity> pedidosEntities) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.correoElectronico = correoElectronico;
        this.clienteEntity = clienteEntity;
        this.pedidosEntities = pedidosEntities;
    }
}
