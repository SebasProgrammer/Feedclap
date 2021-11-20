package com.example.easi641.repository;

import java.util.List;
import java.util.Optional;

import com.example.easi641.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	//si hay errores cambiar esto
	@Query(value = "SELECT u.password FROM users u WHERE u.username = :usernamee", nativeQuery = true)
	Optional<String> getToken(String usernamee);

	@Query(value = "SELECT * FROM users u WHERE u.username = ?1", nativeQuery = true)
	Optional<User> findByUsername(String username);

	@Query(value = "SELECT count(*) FROM users u WHERE u.username = ?1", nativeQuery = true)
	Long countUsername(String username);

	// "STAND_ALONE" = 0;
	// "DEVELOPER" = 1;
	// "REVIEWER" = 2;
	@Query(value = "SELECT * FROM users WHERE type=2", nativeQuery = true)
	List<User> getReviewers();

	@Query(value = "SELECT * FROM users WHERE type=1", nativeQuery = true)
	List<User> getDevelopers();

	@Query(value = "select follower from following where following=?1", nativeQuery = true)
	List<Long> getFollowing(Long id);

	@Query(value = "select following from following where follower=?1", nativeQuery = true)
	List<Long> getFollowers(Long id);
}
