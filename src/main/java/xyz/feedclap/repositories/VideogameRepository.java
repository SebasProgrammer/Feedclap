package xyz.feedclap.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import xyz.feedclap.entities.Videogame;

@Repository
public interface VideogameRepository extends JpaRepository<Videogame,Long>  {
    Optional<Videogame> findById(Long id);
	Optional<Videogame> findByNombre(String nombre);
	
	@Query("SELECT m FROM Videogame m")
	List<Videogame> findAll();
}