package com.example.easi641.controller;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.JuegoDto;
import com.example.easi641.entities.Juego;
import com.example.easi641.services.JuegoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/juegos")
public class JuegoController {
    private final JuegoService juegoService;
    private final EntityDtoConverter entityDtoConverter;

    public JuegoController(JuegoService juegoService, EntityDtoConverter entityDtoConverter) {
        this.juegoService = juegoService;
        this.entityDtoConverter = entityDtoConverter;
    }

    @PostMapping
    public ResponseEntity<JuegoDto> createGame(@Valid @RequestBody JuegoDto juegoDto){
        Juego juego = juegoService.createGame(juegoDto);
        return new ResponseEntity<>(entityDtoConverter.convertJuegoToDto(juego), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JuegoDto>> findAllGames(){
        List<Juego> juegos = juegoService.findAllGames();
        return new ResponseEntity<>(entityDtoConverter.convertJuegoToDto(juegos), HttpStatus.OK);
    }

    @DeleteMapping("/{juegoId}")
    public ResponseEntity<JuegoDto> popGame(@PathVariable Long juegoId){
        juegoService.deleteGame(juegoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{categoria}")
    public ResponseEntity<List<JuegoDto>> findforCategoria(@PathVariable String categoria){
        List<Juego> juegos = juegoService.findforCategoria(categoria);
        return new ResponseEntity<>(entityDtoConverter.convertJuegoToDto(juegos), HttpStatus.OK);
    }
}
