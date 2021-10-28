package com.example.easi641.repository;

import com.example.easi641.entities.DetailGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DetailGameRepository extends JpaRepository<DetailGame, Long> {

    @Query(value = "SELECT juegos_id FROM detallesjuego dj WHERE dj.categorias_id=:categoriaid", nativeQuery = true)
    List<Long> lista_de_juego_por_categoria(Long categoriaid);

    Optional<DetailGame> findById(Long id);
}
