package com.example.easi641.controller;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.JuegoRequest;
import com.example.easi641.dto.JuegoResponse;
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
    public ResponseEntity<JuegoResponse> createGame(@Valid @RequestBody JuegoRequest juegoRequest){
        Juego juego = juegoService.createGame(juegoRequest);
        return new ResponseEntity<>(entityDtoConverter.convertJuegoToDto(juego), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JuegoResponse>> findAllGames(){
        List<Juego> recycles = juegoService.findAllGames();
        return new ResponseEntity<>(entityDtoConverter.convertJuegoToDto(recycles), HttpStatus.OK);
    }

    @DeleteMapping("/{juegoId}")
    public ResponseEntity<JuegoResponse> popGame(@PathVariable Long juegoId){
        juegoService.deleteGame(juegoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
