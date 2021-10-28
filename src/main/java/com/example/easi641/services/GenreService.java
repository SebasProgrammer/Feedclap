package com.example.easi641.services;
import com.example.easi641.common.GenreValidator;
import com.example.easi641.dto.GenreDto;
import com.example.easi641.dto.GenreGameDto;
import com.example.easi641.entities.Genre;
import com.example.easi641.entities.GenreGame;
import com.example.easi641.entities.Juego;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.GenreRepository;
import com.example.easi641.repository.GenreGameRepository;
import com.example.easi641.repository.JuegoRepository;
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
    private GenreGameRepository genreGameRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Genre createGenre(GenreDto genreDto){
        GenreValidator.validateGenre(genreDto);
        Genre genre = new Genre();
        genre.setName(genreDto.getName());

        return genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    public List<Genre> findAllGenres() { return genreRepository.findAll(); }

    @Transactional
    public GenreGame createGenreGame(GenreGameDto genreGameDto){
        Juego juego = juegoRepository.findById(genreGameDto.getJuegoId())
                .orElseThrow(() -> new NotFoundException("Game not found."));

        Genre genre = genreRepository.findById(genreGameDto.getGenreId())
                .orElseThrow(() -> new NotFoundException("Genre not found."));

        GenreGame genreGame = new GenreGame();
        genreGame.setJuego(juego);
        genreGame.setGenre(genre);
        return genreGameRepository.save(genreGame);
    }

}
