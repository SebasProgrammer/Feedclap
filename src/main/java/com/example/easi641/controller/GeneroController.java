package com.example.easi641.controller;
import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.*;
import com.example.easi641.dto.GeneroJuegoDto;
import com.example.easi641.entities.Genero;
import com.example.easi641.entities.GeneroJuego;
import com.example.easi641.services.GeneroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {
    private final GeneroService generoService;

    private final EntityDtoConverter entityDtoConverter;

    public GeneroController(GeneroService generoService, EntityDtoConverter entityDtoConverter) {
        this.generoService = generoService;
        this.entityDtoConverter = entityDtoConverter;
    }

    @PostMapping
    public ResponseEntity<GeneroDto> createGenero(@Valid @RequestBody GeneroDto generoDto){
        Genero genero = generoService.createGenero(generoDto);
        return new ResponseEntity<>(entityDtoConverter.convertGeneroToDto(genero), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GeneroDto>> findAllGeneros(){
        List<Genero> generos = generoService.findAllGeneros();
        return new ResponseEntity<>(entityDtoConverter.convertGeneroToDto(generos), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/GeneroJuego")
    public ResponseEntity<GeneroJuegoDto> createGeneroJuego(@Valid @RequestBody GeneroJuegoDto generoJuegoDto){
        GeneroJuego generoJuego = generoService.createGeneroJuego(generoJuegoDto);
        return new ResponseEntity<>(entityDtoConverter.convertGeneroJuegoToDto(generoJuego), HttpStatus.CREATED);
    }
}
