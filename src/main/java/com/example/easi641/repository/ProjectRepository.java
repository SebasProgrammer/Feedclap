package com.example.easi641.repository;

import com.example.easi641.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	@Query(value = "SELECT p.game_id FROM projects p WHERE p.developer_id=:developerId", nativeQuery = true)
	List<Long> getGamesByDeveloper(Long developerId);

	@Query(value = "SELECT p.developer_id FROM projects p WHERE p.game_id=:gameId", nativeQuery = true)
	List<Long> getDevelopersByGame(Long gameId);

}
