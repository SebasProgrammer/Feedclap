package com.example.easi641.controller;
import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.*;
import com.example.easi641.dto.GenreGameDto;
import com.example.easi641.entities.Genre;
import com.example.easi641.entities.GenreGame;
import com.example.easi641.services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/generos")
public class GenreController {
    private final GenreService genreService;

    private final EntityDtoConverter entityDtoConverter;

    public GenreController(GenreService genreService, EntityDtoConverter entityDtoConverter) {
        this.genreService = genreService;
        this.entityDtoConverter = entityDtoConverter;
    }

    @PostMapping
    public ResponseEntity<GenreDto> createGenre(@Valid @RequestBody GenreDto genreDto){
        Genre genre = genreService.createGenre(genreDto);
        return new ResponseEntity<>(entityDtoConverter.convertGenreToDto(genre), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GenreDto>> findAllGenres(){
        List<Genre> genres = genreService.findAllGenres();
        return new ResponseEntity<>(entityDtoConverter.convertGenreToDto(genres), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/GeneroJuego")
    public ResponseEntity<GenreGameDto> createGeneroGame(@Valid @RequestBody GenreGameDto genreGameDto){
        GenreGame genreGame = genreService.createGenreGame(genreGameDto);
        return new ResponseEntity<>(entityDtoConverter.convertGenreGameToDto(genreGame), HttpStatus.CREATED);
    }
}
