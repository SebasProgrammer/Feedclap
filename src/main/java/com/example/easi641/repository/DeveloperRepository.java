package com.example.easi641.repository;

import com.example.easi641.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
	@Query(value = "SELECT d.id FROM developers d WHERE d.name=:developerName", nativeQuery = true)
	Long getDeveloperId(String developerName);
}
