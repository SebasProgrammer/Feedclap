package com.example.easi641.repository;

import com.example.easi641.entities.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Long> {
    @Query(value = "SELECT * FROM juegos j WHERE j.nombre = :name", nativeQuery = true)
    List<Juego> gamesnamessimilar(String name);

    @Query(value = "SELECT descripcion FROM juegos j WHERE j.nombre = :name", nativeQuery = true)
    String gamename(String name);

    @Query(value = "SELECT j.descarga FROM juegos j WHERE j.nombre = :name", nativeQuery = true)
    String gameurl(String name);
}
