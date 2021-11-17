package com.example.easi641.controller;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.GameDto;
import com.example.easi641.dto.GenreDto;
import com.example.easi641.entities.Game;
import com.example.easi641.entities.Genre;
import com.example.easi641.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
	private final GameService gameService;
	private final EntityDtoConverter entityDtoConverter;

	public GameController(GameService gameService, EntityDtoConverter entityDtoConverter) {
		this.gameService = gameService;
		this.entityDtoConverter = entityDtoConverter;
	}

	@PostMapping
	public ResponseEntity<GameDto> createGame(@Valid @RequestBody GameDto gameDto) {
		Game game = gameService.createGame(gameDto);
		return new ResponseEntity<>(entityDtoConverter.convertGameToDto(game), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<GameDto>> findAllGames() {
		List<Game> games = gameService.findAllGames();
		return new ResponseEntity<>(entityDtoConverter.convertGameToDto(games), HttpStatus.OK);
	}

	@DeleteMapping("/{gameId}")
	public ResponseEntity<GameDto> popGame(@PathVariable Long gameId) {
		gameService.deleteGame(gameId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<GameDto>> getByCategory(@PathVariable String category) {
		List<Game> games = gameService.getByCategory(category);
		return new ResponseEntity<>(entityDtoConverter.convertGameToDto(games), HttpStatus.OK);
	}

	@GetMapping("/genres/{genre}")
	public ResponseEntity<List<GameDto>> getByGenre(@PathVariable String genre) {
		List<Game> games = gameService.getByGenre(genre);
		return new ResponseEntity<>(entityDtoConverter.convertGameToDto(games), HttpStatus.OK);
	}

	@GetMapping("/genre/{gameName}")
	public ResponseEntity<List<GenreDto>> getGenresoftheGame(@PathVariable String gameName) {
		List<Genre> genres = gameService.getGenres_game(gameName);
		return new ResponseEntity<>(entityDtoConverter.convertGenreToDto(genres), HttpStatus.OK);
	}

	@GetMapping("/search/{gameName}")
	public ResponseEntity<GameDto> findGameByName(@PathVariable String gameName) {
		Game games = gameService.getGameByName(gameName);
		return new ResponseEntity<>(entityDtoConverter.convertGameToDto(games), HttpStatus.OK);
	}

	@GetMapping("/descripcion/{gameName}")
	public ResponseEntity<String> description_Game(@PathVariable String gameName) {
		return new ResponseEntity<>(gameService.getDescription(gameName), HttpStatus.OK);
	}

	@GetMapping("/download/{gameName}")
	public ResponseEntity<String> getGameDownloadLink(@PathVariable String gameName) {
		return new ResponseEntity<>(gameService.getDownloadLink(gameName), HttpStatus.OK);
	}

	@GetMapping("/developer/{developerUsername}")
	public ResponseEntity<List<GameDto>> findGamesforDeveloper(@PathVariable String developerUsername) {
		List<Game> games = gameService.getByDeveloper(developerUsername);
		return new ResponseEntity<>(entityDtoConverter.convertGameToDto(games), HttpStatus.OK);
	}

}
