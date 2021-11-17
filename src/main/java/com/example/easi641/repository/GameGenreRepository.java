package com.example.easi641.repository;

import java.util.List;
import java.util.Optional;

import com.example.easi641.entities.GameGenre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameGenreRepository extends JpaRepository<GameGenre, Long> {
	@Query(value = "SELECT g.game_id FROM gamegenres g WHERE g.genre_id=:genreId", nativeQuery = true)
	List<Long> getGamesByGenre(Long genreId);

	@Query(value = "SELECT g.genre_id FROM gamegenres g WHERE g.game_id=:gameId", nativeQuery = true)
	List<Long> getGenresByGame(Long gameId);

	Optional<GameGenre> findById(Long id);
}
