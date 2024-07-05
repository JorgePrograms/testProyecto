package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.Categoria;
import com.example.tiendaElectronica.domain.ports.out.CategoriaRepositoryPort;
import com.example.tiendaElectronica.infraestructure.entity.CategoriaEntity;
import com.example.tiendaElectronica.infraestructure.mapper.CategoriaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoriaJpaRepositoryAdapter implements CategoriaRepositoryPort {
    private final CategoriaJpaRepository categoriaJpaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaJpaRepositoryAdapter(CategoriaJpaRepository categoriaJpaRepository,CategoriaMapper categoriaMapper) {
        this.categoriaJpaRepository = categoriaJpaRepository;
        this.categoriaMapper=categoriaMapper;
    }

    @Override
    public Categoria save(Categoria categoria) {
        CategoriaEntity categoriaEntity=categoriaMapper.toCategoriaEntity(categoria);
        CategoriaEntity saveCategoriaEntity=categoriaJpaRepository.save(categoriaEntity);
        return categoriaMapper.toCategoria(saveCategoriaEntity);
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaJpaRepository.findById(id).map(categoriaMapper::toCategoria);
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaJpaRepository.findAll().stream().map(categoriaMapper::toCategoria)
                .collect(Collectors.toList());
    }
}
