package com.example.easi641.services;

import com.example.easi641.common.GenreValidator;
import com.example.easi641.dto.GenreDto;
import com.example.easi641.dto.GameGenreDto;
import com.example.easi641.entities.Genre;
import com.example.easi641.entities.GameGenre;
import com.example.easi641.entities.Game;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.GenreRepository;
import com.example.easi641.repository.GameGenreRepository;
import com.example.easi641.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreService {
	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private GameGenreRepository gameGenreRepository;

	@Autowired
	private GameRepository gameRepository;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Genre createGenre(GenreDto genreDto) {
		GenreValidator.validateGenre(genreDto);
		Genre genre = new Genre();
		genre.setName(genreDto.getName());
		return genreRepository.save(genre);
	}

	@Transactional(readOnly = true)
	public List<Genre> findAllGenres() {
		return genreRepository.findAll();
	}

	@Transactional
	public GameGenre createGameGenre(GameGenreDto gameGenreDto) {
		Genre genre = genreRepository.findById(gameGenreDto.getGenreId())
				.orElseThrow(() -> new NotFoundException("Genre not found."));

		Game game = gameRepository.findById(gameGenreDto.getGameId())
				.orElseThrow(() -> new NotFoundException("Game not found."));

		GameGenre gameGenre = new GameGenre(genre, game);
		return gameGenreRepository.save(gameGenre);
	}

}
