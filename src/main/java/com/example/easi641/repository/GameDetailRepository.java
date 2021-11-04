package com.example.easi641.repository;

import com.example.easi641.entities.GameDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameDetailRepository extends JpaRepository<GameDetail, Long> {

	@Query(value = "SELECT game_id FROM gamedetails gd WHERE gd.category_id=:categoryId", nativeQuery = true)
	List<Long> getGamesByCategory(Long categoryId);

	Optional<GameDetail> findById(Long id);
}
