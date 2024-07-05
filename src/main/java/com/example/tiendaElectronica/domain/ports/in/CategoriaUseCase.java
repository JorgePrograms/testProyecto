package com.example.tiendaElectronica.domain.ports.in;

import com.example.tiendaElectronica.domain.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaUseCase {
    Categoria crearCategoria(Categoria categoria);
    List<Categoria> getAllCategorias();
    Optional<Categoria> getCategoria(Long id);
}
