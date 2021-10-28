package com.example.easi641.repository;

import com.example.easi641.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
	@Query(value = "SELECT d.name FROM developeres d WHERE d.name = :name", nativeQuery = true)
	List<Developer> nameDeveloper(String name);

	@Query(value = "SELECT id FROM developers d WHERE d.name=:name_developer", nativeQuery = true)
	Long lista_de_juego_por_developer(String name_developer);
}
