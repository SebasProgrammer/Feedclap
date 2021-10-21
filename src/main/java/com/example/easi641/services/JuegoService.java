package com.example.easi641.services;

import com.example.easi641.common.JuegoValidator;
import com.example.easi641.dto.JuegoRequest;
import com.example.easi641.entities.Juego;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.JuegoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JuegoService {
    private final JuegoRepository juegoRepository;

    public JuegoService(JuegoRepository juegoRepository){
        this.juegoRepository = juegoRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Juego createGame(JuegoRequest juegoRequest){
        JuegoValidator.validateGame(juegoRequest);
        Juego juego = Juego.builder()
                .nombre(juegoRequest.getNombre())
                .descripcion(juegoRequest.getDescripcion())
                .build();

        return juegoRepository.save(juego);
    }

    @Transactional(readOnly = true)
    public List<Juego> findAllGames(){
        return juegoRepository.findAll();
    }

    @Transactional
    public void deleteGame(Long juegoId){
        Juego juego = juegoRepository.findById(juegoId)
                .orElseThrow(()-> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
        juegoRepository.delete(juego);
    }
}
