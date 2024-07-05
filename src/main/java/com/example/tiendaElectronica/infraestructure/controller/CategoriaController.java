package com.example.tiendaElectronica.infraestructure.controller;

import com.example.tiendaElectronica.application.service.CategoriaService;
import com.example.tiendaElectronica.domain.model.Categoria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categoria")
@RestController
public class CategoriaController {
    private final CategoriaService categoriaService;


    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria){
        Categoria createCategoria=categoriaService.crearCategoria(categoria);
        return new ResponseEntity<>(createCategoria, HttpStatus.CREATED);
    }
    @GetMapping("/{categoriaId}")
    public ResponseEntity<Categoria> findByIdCategoria(@PathVariable Long categoriaId){
        return categoriaService.getCategoria(categoriaId).
                map(categoria -> new ResponseEntity<>(categoria,HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Categoria> getAllCategorias(){
        return categoriaService.getAllCategorias();
    }







}
