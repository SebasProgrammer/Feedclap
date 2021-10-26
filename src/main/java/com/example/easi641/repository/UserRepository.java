package com.example.easi641.repository;

import com.example.easi641.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT count(*) as 'count' FROM users u WHERE u.username = ?1", nativeQuery = true)
	Long countUsername(String username);

}
