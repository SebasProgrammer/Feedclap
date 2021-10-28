package com.example.easi641.repository;

import java.util.List;
import java.util.Optional;

import com.example.easi641.entities.GeneroJuego;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroJuegoRepository extends JpaRepository<GeneroJuego, Long> {
	@Query(value = "SELECT gj.juegos_id FROM genero_juegos gj WHERE gj.generos_id=:generoId", nativeQuery = true)
	List<Long> lista_juego_genero(Long generoId);

	Optional<GeneroJuego> findById(Long id);

}
