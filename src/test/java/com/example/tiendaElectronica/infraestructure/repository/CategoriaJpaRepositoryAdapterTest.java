package com.example.tiendaElectronica.infraestructure.repository;

import com.example.tiendaElectronica.domain.model.Categoria;
import com.example.tiendaElectronica.infraestructure.entity.CategoriaEntity;
import com.example.tiendaElectronica.infraestructure.mapper.CategoriaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoriaJpaRepositoryAdapterTest {

    @Mock
    CategoriaJpaRepository categoriaJpaRepository;
    @Mock
    CategoriaMapper categoriaMapper;

    @InjectMocks
    CategoriaJpaRepositoryAdapter categoriaJpaRepositoryAdapter;

    @BeforeEach
    void setUp() {
            MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Categoria categoria=new Categoria(2L,"almacenamiento",new HashSet<>());
        CategoriaEntity categoriaEntity=new CategoriaEntity(2L,"almacenamiento",new HashSet<>());

        when(categoriaMapper.toCategoriaEntity(categoria)).thenReturn(categoriaEntity);
        when(categoriaMapper.toCategoria(categoriaEntity)).thenReturn(categoria);

        when(categoriaJpaRepository.save(categoriaEntity)).thenReturn(categoriaEntity);

        Categoria categoriaAdapter=categoriaJpaRepositoryAdapter.save(categoria);

        assertNotNull(categoriaAdapter);

        assertEquals(categoriaAdapter.getNombre(),categoria.getNombre());
    }

    @Test
    void findById() {
    Long id=2L;
    CategoriaEntity categoriaEntity=new CategoriaEntity(id,"almacenamiento",new HashSet<>());

    when(categoriaJpaRepository.findById(id)).thenReturn(Optional.of(categoriaEntity));
    Categoria categoria=new Categoria(id,"almacenamiento",new HashSet<>());
    when(categoriaMapper.toCategoria(categoriaEntity)).thenReturn(categoria);
    Optional<Categoria> categoriaAdapter=categoriaJpaRepositoryAdapter.findById(id);

    Categoria categoriaExitoso=categoriaAdapter.get();

    assertNotNull(categoriaExitoso);

    assertEquals(categoriaExitoso.getNombre(),categoriaEntity.getNombre());
    }
    @Test
    void findAll(){
        CategoriaEntity categoriaEntity1=new CategoriaEntity(1L,"lapton",new HashSet<>());
        CategoriaEntity categoriaEntity2=new CategoriaEntity(2L,"tarjetas graficas",new HashSet<>());
        List<CategoriaEntity>categoriaEntities= Arrays.asList(categoriaEntity1,categoriaEntity2);

        when(categoriaJpaRepository.findAll()).thenReturn(categoriaEntities);

        List<Categoria> categoriaAdapter=categoriaJpaRepositoryAdapter.findAll();

        assertNotNull(categoriaAdapter);
        assertEquals(categoriaAdapter.size(),categoriaEntities.size());
        assertEquals(categoriaAdapter.containsAll(categoriaEntities),categoriaEntities.containsAll(categoriaAdapter));
    }
}