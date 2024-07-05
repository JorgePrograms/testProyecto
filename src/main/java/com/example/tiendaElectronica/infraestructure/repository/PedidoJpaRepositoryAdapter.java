package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.DetallePedido;
import com.example.tiendaElectronica.domain.model.Pedido;
import com.example.tiendaElectronica.domain.model.Producto;
import com.example.tiendaElectronica.domain.ports.out.PedidoRepositoryPort;
import com.example.tiendaElectronica.infraestructure.entity.DetallePedidoEntity;
import com.example.tiendaElectronica.infraestructure.entity.PedidoEntity;
import com.example.tiendaElectronica.infraestructure.mapper.PedidoMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class PedidoJpaRepositoryAdapter implements PedidoRepositoryPort {
    private final PedidoJpaRepository pedidoJpaRepository;
    private final PedidoMapper pedidoMapper;


    public PedidoJpaRepositoryAdapter(PedidoJpaRepository pedidoJpaRepository, PedidoMapper pedidoMapper) {
        this.pedidoJpaRepository = pedidoJpaRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public Pedido save(Pedido pedido) {


        PedidoEntity pedidoEntity=pedidoMapper.toPedidoEntity(pedido);

        for(DetallePedidoEntity detalle: pedidoEntity.getDetallePedidoEntities()){
            detalle.actualizarStock();
        }
        pedidoEntity.calcularMontoTotal();
        PedidoEntity savePedidoEntity=pedidoJpaRepository.save(pedidoEntity);
        return pedidoMapper.toPedido(savePedidoEntity);
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        Optional<PedidoEntity> optionalPedidoEntity = pedidoJpaRepository.findById(id);
        return optionalPedidoEntity.map(pedidoEntity -> {
            pedidoEntity.calcularMontoTotal(); // Aseguramos que el monto total esté calculado
            return pedidoMapper.toPedido(pedidoEntity);
        });
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoJpaRepository.findAll().stream().map(pedidoMapper::toPedido).
                collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> update(Long id, Pedido pedido) {
        if(id!=null && pedidoJpaRepository.existsById(id)){
         PedidoEntity pedidoEntity=pedidoMapper.toPedidoEntity(pedido);
         pedidoEntity.setId(id);

            for (DetallePedidoEntity detalle : pedidoEntity.getDetallePedidoEntities()) {
                detalle.actualizarStock();
            }
            pedidoEntity.calcularMontoTotal();

         PedidoEntity updatePedidoEntity=pedidoJpaRepository.save(pedidoEntity);
         Pedido updatePedido=pedidoMapper.toPedido(updatePedidoEntity);
         return Optional.of(updatePedido);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        if(pedidoJpaRepository.existsById(id)){
            pedidoJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }




}