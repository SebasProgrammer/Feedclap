package com.example.easi641.services;

import com.example.easi641.common.CategoriaValidator;
import com.example.easi641.dto.CategoriaDto;
import com.example.easi641.dto.DetalleJuegoDto;
import com.example.easi641.entities.Categoria;
import com.example.easi641.entities.DetalleJuego;
import com.example.easi641.entities.Juego;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.CategoriaRepository;
import com.example.easi641.repository.DetalleJuegoRepository;
import com.example.easi641.repository.JuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private DetalleJuegoRepository detalleJuegoRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Categoria createCategorias(CategoriaDto categoriaDto){
        CategoriaValidator.validateCategoria(categoriaDto);
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDto.getNombre());

        return categoriaRepository.save(categoria);
    }

    @Transactional(readOnly = true)
    public List<Categoria> findAllCategorias(){
        return categoriaRepository.findAll();
    }


    @Transactional
    public DetalleJuego createCredito(DetalleJuegoDto detalleJuegoDto) {
        Juego juego = juegoRepository.findById(detalleJuegoDto.getJuegoId())
                .orElseThrow(() -> new NotFoundException("Game not found."));

        Categoria categoria = categoriaRepository.findById(detalleJuegoDto.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria not found."));

        DetalleJuego detalleJuego = new DetalleJuego();
        detalleJuego.setJuego(juego);
        detalleJuego.setCategoria(categoria);
        return detalleJuegoRepository.save(detalleJuego);
    }
}
