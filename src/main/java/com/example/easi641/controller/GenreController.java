package com.example.easi641.controller;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.*;
import com.example.easi641.dto.GameGenreDto;
import com.example.easi641.entities.Genre;
import com.example.easi641.entities.GameGenre;
import com.example.easi641.services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {
	private final GenreService genreService;

	private final EntityDtoConverter entityDtoConverter;

	public GenreController(GenreService genreService, EntityDtoConverter entityDtoConverter) {
		this.genreService = genreService;
		this.entityDtoConverter = entityDtoConverter;
	}

	@PostMapping
	public ResponseEntity<GenreDto> createGenre(@Valid @RequestBody GenreDto genreDto) {
		Genre genre = genreService.createGenre(genreDto);
		return new ResponseEntity<>(entityDtoConverter.convertGenreToDto(genre), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<GenreDto>> findAllGenres() {
		List<Genre> genres = genreService.findAllGenres();
		return new ResponseEntity<>(entityDtoConverter.convertGenreToDto(genres), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/gamegenre")
	public ResponseEntity<GameGenreDto> createGameGenre(@Valid @RequestBody GameGenreDto genreJuegoDto) {
		GameGenre genreJuego = genreService.createGameGenre(genreJuegoDto);
		return new ResponseEntity<>(entityDtoConverter.convertGameGenreToDto(genreJuego), HttpStatus.CREATED);
	}
}
