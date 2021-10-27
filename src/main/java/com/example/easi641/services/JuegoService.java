package com.example.easi641.services;

import com.example.easi641.common.JuegoValidator;
import com.example.easi641.dto.JuegoDto;
import com.example.easi641.entities.Juego;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.CategoriaRepository;
import com.example.easi641.repository.DetalleJuegoRepository;
import com.example.easi641.repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class JuegoService {
    @Autowired
    private  JuegoRepository juegoRepository;
    @Autowired
    private DetalleJuegoRepository detalleJuegoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Juego createGame(JuegoDto juegoDto){
        JuegoValidator.validateGame(juegoDto);
        Juego juego = Juego.builder()
                .nombre(juegoDto.getNombre())
                .descripcion(juegoDto.getDescripcion())
                .build();

        return juegoRepository.save(juego);
    }

    @Transactional(readOnly = true)
    public List<Juego> findAllGames(){
        return juegoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Juego> findGamesNames(String name_game){
        return juegoRepository.gamesnamessimilar(name_game);
    }

    @Transactional(readOnly = true)
    public String descipcion_Game(String nombre_videojuego){
        return juegoRepository.gamename(nombre_videojuego);
    }

    @Transactional
    public void deleteGame(Long juegoId){
        Juego juego = juegoRepository.findById(juegoId)
                .orElseThrow(()-> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
        juegoRepository.delete(juego);
    }

    @Transactional(readOnly = true)
    public List<Juego> findforCategoria(String categoria_name){
        Long categoriaid= categoriaRepository.lista_de_juego_por_categoria(categoria_name);
        List<Long> waaa = detalleJuegoRepository.lista_de_juego_por_categoria(categoriaid);
        List<Juego> weeee= new ArrayList<>();

        for(int i = 0; i<waaa.size(); i++){
            weeee.add(juegoRepository.getById(waaa.get(i)));
        }
        return weeee;
    }
}
