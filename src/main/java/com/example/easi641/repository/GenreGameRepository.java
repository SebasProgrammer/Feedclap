package com.example.easi641.repository;

import java.util.List;
import java.util.Optional;

import com.example.easi641.entities.GenreGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreGameRepository extends JpaRepository<GenreGame, Long> {
    @Query(value = "SELECT gg.juegos_id FROM GenreGames gg WHERE gg.genres_id=:genreId", nativeQuery = true)
    List<Long> list_game_genre(Long generoId);

    Optional<GenreGame> findById(Long id);

}
