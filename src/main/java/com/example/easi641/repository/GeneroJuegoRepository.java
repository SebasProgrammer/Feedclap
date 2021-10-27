package com.example.easi641.repository;

import com.example.easi641.entities.GeneroJuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroJuegoRepository extends JpaRepository<GeneroJuego, Long> {
}
