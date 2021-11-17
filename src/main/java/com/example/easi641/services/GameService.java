package com.example.easi641.services;

import com.example.easi641.common.GameValidator;
import com.example.easi641.dto.GameDto;
import com.example.easi641.entities.Game;
import com.example.easi641.entities.Genre;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private DeveloperRepository developerRepository;

	@Autowired
	private GameGenreRepository gameGenreRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private GameDetailRepository gameDetailRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Game createGame(GameDto gameDto) {
		GameValidator.validateGame(gameDto);
		Game game = Game.builder().name(gameDto.getName()).description(gameDto.getDescription())
				.downloadLink(gameDto.getDownloadLink()).reviewPrice(gameDto.getReviewPrice()).build();
		return gameRepository.save(game);
	}

	@Transactional(readOnly = true)
	public List<Game> findAllGames() {
		return gameRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Game getGameByName(String gameName) {
		return gameRepository.getByName(gameName);
	}

	@Transactional(readOnly = true)
	public String getDescription(String gameName) {
		return gameRepository.getDescription(gameName);
	}

	@Transactional
	public void deleteGame(Long gameId) {
		Game game = gameRepository.findById(gameId)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
		gameRepository.delete(game);
	}

	@Transactional(readOnly = true)
	public String getDownloadLink(String gameName) {
		return gameRepository.getDownloadLink(gameName);
	}

	List<Game> getGamesByIds(List<Long> gameIds) {
		List<Game> gamesArray = new ArrayList<>();
		for (Long gameId : gameIds) {
			gamesArray.add(gameRepository.getById(gameId));
		}
		return gamesArray;
	}

	List<Genre> getGenresByIds(List<Long> genreids) {
		List<Genre> genresArray = new ArrayList<>();
		for (Long genreid : genreids) {
			genresArray.add(genreRepository.getById(genreid));
		}
		return genresArray;
	}

	@Transactional(readOnly = true)
	public List<Game> getByCategory(String categoryName) {
		Long categoryId = categoryRepository.getCategoryId(categoryName);
		return getGamesByIds(gameDetailRepository.getGamesByCategory(categoryId));
	}

	@Transactional(readOnly = true)
	public List<Game> getByGenre(String genreName) {
		Long genreId = genreRepository.getGenreId(genreName);
		return getGamesByIds(gameGenreRepository.getGamesByGenre(genreId));
	}

	@Transactional(readOnly = true)
	public List<Game> getByDeveloper(String developerName) {
		Long desarrolladorId = developerRepository.getDeveloperId(developerName);
		return getGamesByIds(projectRepository.getGamesByDeveloper(desarrolladorId));
	}

	@Transactional(readOnly = true)
	public List<Genre> getGenres_game(String gameName) {
		Game game=getGameByName(gameName);
		Long gameid=game.getId();
		List<Long> generosid=gameGenreRepository.getGenresByGame(gameid);
		List<Genre> generossss=getGenresByIds(generosid);
		return generossss;
	}
}
