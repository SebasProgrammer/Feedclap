package com.example.easi641.repository;

import com.example.easi641.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT id FROM categorias c WHERE c.name=:name_category", nativeQuery = true)
    Long GameforCategory(String name_category);

}
