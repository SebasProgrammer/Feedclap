package com.example.easi641.services;
import com.example.easi641.common.GeneroValidator;
import com.example.easi641.dto.GeneroDto;
import com.example.easi641.dto.GeneroJuegoDto;
import com.example.easi641.entities.Genero;
import com.example.easi641.entities.GeneroJuego;
import com.example.easi641.entities.Juego;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.GeneroRepository;
import com.example.easi641.repository.GeneroJuegoRepository;
import com.example.easi641.repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private GeneroJuegoRepository generoJuegoRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Genero createGenero(GeneroDto generoDto){
        GeneroValidator.validateGenero(generoDto);
        Genero genero = new Genero();
        genero.setNombre(generoDto.getNombre());

        return generoRepository.save(genero);
    }

    @Transactional(readOnly = true)
    public List<Genero> findAllGeneros() { return generoRepository.findAll(); }

    @Transactional
    public GeneroJuego createGeneroJuego(GeneroJuegoDto generoJuegoDto){
        Juego juego = juegoRepository.findById(generoJuegoDto.getJuegoId())
                .orElseThrow(() -> new NotFoundException("Game not found."));

        Genero genero = generoRepository.findById(generoJuegoDto.getGeneroId())
                .orElseThrow(() -> new NotFoundException("Genero not found."));

        GeneroJuego generoJuego = new GeneroJuego();
        generoJuego.setJuego(juego);
        generoJuego.setGenero(genero);
        return generoJuegoRepository.save(generoJuego);
    }

}
