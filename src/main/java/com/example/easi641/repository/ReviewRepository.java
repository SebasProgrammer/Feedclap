package com.example.easi641.repository;

import com.example.easi641.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "SELECT * FROM reviews r WHERE r.reviewer = :userid", nativeQuery = true)
    List<Review> getreviewsbyuser(Long userid);
}
