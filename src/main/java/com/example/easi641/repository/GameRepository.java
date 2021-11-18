package com.example.easi641.repository;

import com.example.easi641.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
	@Query(value = "SELECT * FROM games g WHERE g.name = :name", nativeQuery = true)
	Game getByName(String name);

	@Query(value = "SELECT description FROM games g WHERE g.name = :name", nativeQuery = true)
	String getDescription(String name);

	@Query(value = "SELECT g.download_link FROM games g WHERE g.name = :name", nativeQuery = true)
	String getDownloadLink(String name);

	@Query(value = "SELECT * FROM games g WHERE g.name LIKE %:keyword%", nativeQuery = true)
	List<Game> getgameBykeyword(String keyword);

}
