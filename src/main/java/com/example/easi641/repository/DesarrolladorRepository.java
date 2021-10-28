package com.example.easi641.repository;

import com.example.easi641.entities.Desarrollador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DesarrolladorRepository extends JpaRepository<Desarrollador, Long> {
    @Query(value = "SELECT d.nombre FROM desarrolladores d WHERE d.nombre = :name", nativeQuery = true)
    List<Desarrollador> nombreDesarrollador(String name);

    @Query(value = "SELECT id FROM desarrolladores d WHERE d.nombre=:nombre_desarrollador", nativeQuery = true)
    Long lista_de_juego_por_desarrollador(String nombre_desarrollador);
}
