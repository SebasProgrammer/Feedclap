package com.example.easi641.repository;

import com.example.easi641.entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    @Query(value = "SELECT p.juegos_id FROM proyectos p WHERE p.desarrolladores_id=:desarrolladorId", nativeQuery = true)
    List<Long> lista_proyecto(Long desarrolladorId);

}
