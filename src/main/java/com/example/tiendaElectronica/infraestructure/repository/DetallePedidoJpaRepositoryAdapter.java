package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.DetallePedido;
import com.example.tiendaElectronica.domain.ports.out.DetallePedidoRepositoryPort;
import com.example.tiendaElectronica.infraestructure.entity.DetallePedidoEntity;
import com.example.tiendaElectronica.infraestructure.mapper.DetallePedidoMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DetallePedidoJpaRepositoryAdapter implements DetallePedidoRepositoryPort {
    private final DetallePedidoJpaRepository detallePedidoJpaRepository;
    private final DetallePedidoMapper detallePedidoMapper;

    public DetallePedidoJpaRepositoryAdapter(DetallePedidoJpaRepository detallePedidoJpaRepository, DetallePedidoMapper detallePedidoMapper) {
        this.detallePedidoJpaRepository = detallePedidoJpaRepository;
        this.detallePedidoMapper = detallePedidoMapper;
    }

    @Override
    public DetallePedido save(DetallePedido detallePedido) {
        DetallePedidoEntity detallePedidoEntity=detallePedidoMapper.toDetallePedidoEntity(detallePedido);
        DetallePedidoEntity saveDetallePedidoEntity=detallePedidoJpaRepository.save(detallePedidoEntity);

        return detallePedidoMapper.toDetallePedido(saveDetallePedidoEntity);
    }

    @Override
    public Optional<DetallePedido> findById(Long id) {
        return detallePedidoJpaRepository.findById(id).map(detallePedidoMapper::toDetallePedido);
    }

    @Override
    public List<DetallePedido> findAll() {
        return detallePedidoJpaRepository.findAll().stream().
                map(detallePedidoMapper::toDetallePedido).collect(Collectors.toList());
    }
}
