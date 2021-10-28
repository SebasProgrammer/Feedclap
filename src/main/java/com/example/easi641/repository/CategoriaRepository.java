package com.example.easi641.repository;

import com.example.easi641.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	@Query(value = "SELECT id FROM categorias c WHERE c.nombre=:nombre_categoria", nativeQuery = true)
	Long lista_de_juego_por_categoria(String nombre_categoria);

}
