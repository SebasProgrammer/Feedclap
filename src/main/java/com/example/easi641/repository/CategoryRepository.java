package com.example.easi641.repository;

import java.util.List;

import com.example.easi641.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query(value = "SELECT id FROM categories c WHERE c.name=:name_category", nativeQuery = true)
	Long getCategoryId(String name_category);

	@Query(value = "SELECT gd.category_id FROM gamedetails gd WHERE game_id=:gameId", nativeQuery = true)
	List<Long> getCategoriesByGame(Long gameId);
}
