package com.example.easi641.repository;

import com.example.easi641.entities.DetalleJuego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetalleJuegoRepository extends JpaRepository<DetalleJuego, Long> {
    Optional<DetalleJuego> findById(Long id);

}
