package com.example.easi641.repository;

import com.example.easi641.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
	@Query(value = "SELECT g.id FROM genres g WHERE g.name=:genreName", nativeQuery = true)
	Long getGenreId(String genreName);

}
