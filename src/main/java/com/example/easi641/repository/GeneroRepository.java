package com.example.easi641.repository;

import com.example.easi641.entities.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
    @Query(value = "SELECT g.id FROM generos g WHERE g.nombre =:nombre_genero", nativeQuery = true)
    Long lista_de_juego_por_genero(String nombre_genero);

}
