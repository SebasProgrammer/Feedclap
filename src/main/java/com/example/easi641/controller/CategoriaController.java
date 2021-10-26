package com.example.easi641.controller;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.*;
import com.example.easi641.dto.DetalleJuegoDto;
import com.example.easi641.entities.Categoria;
import com.example.easi641.entities.DetalleJuego;
import com.example.easi641.services.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    private final EntityDtoConverter entityDtoConverter;

    public CategoriaController(CategoriaService categoriaService, EntityDtoConverter entityDtoConverter) {
        this.categoriaService = categoriaService;
        this.entityDtoConverter = entityDtoConverter;
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> createCategoria(@Valid @RequestBody CategoriaDto categoriaDto){
        Categoria categoria = categoriaService.createCategorias(categoriaDto);
        return new ResponseEntity<>(entityDtoConverter.convertCategoriaToDto(categoria), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAllCategorias(){
        List<Categoria> categorias = categoriaService.findAllCategorias();
        return new ResponseEntity<>(entityDtoConverter.convertCategoriaToDto(categorias), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/credito")
    public ResponseEntity<DetalleJuegoDto> createCreditos(@Valid @RequestBody DetalleJuegoDto detalleJuegoDto){
        DetalleJuego detalleJuego = categoriaService.createCredito(detalleJuegoDto);
        return new ResponseEntity<>(entityDtoConverter.convertDetalleJuegoToDto(detalleJuego), HttpStatus.CREATED);
    }

}
