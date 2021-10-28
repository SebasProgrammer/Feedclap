package com.example.easi641.services;

import com.example.easi641.common.JuegoValidator;
import com.example.easi641.dto.JuegoDto;
import com.example.easi641.entities.Juego;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.*;
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
	ProyectoRepository proyectoRepository;

	@Autowired
	private DeveloperRepository developerRepository;

	@Autowired
	private GeneroJuegoRepository generoJuegoRepository;

	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private JuegoRepository juegoRepository;
	@Autowired
	private DetalleJuegoRepository detalleJuegoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Juego createGame(JuegoDto juegoDto) {
		JuegoValidator.validateGame(juegoDto);
		Juego juego = Juego.builder().nombre(juegoDto.getNombre()).descripcion(juegoDto.getDescripcion())
				.descarga(juegoDto.getDescarga()).build();

		return juegoRepository.save(juego);
	}

	@Transactional(readOnly = true)
	public List<Juego> findAllGames() {
		return juegoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<Juego> findGamesNames(String name_game) {
		return juegoRepository.gamesnamessimilar(name_game);
	}

	@Transactional(readOnly = true)
	public String descipcion_Game(String nombre_videojuego) {
		return juegoRepository.gamename(nombre_videojuego);
	}

	@Transactional
	public void deleteGame(Long juegoId) {
		Juego juego = juegoRepository.findById(juegoId)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
		juegoRepository.delete(juego);
	}

	@Transactional(readOnly = true)
	public List<Juego> findforCategoria(String categoria_name) {
		Long categoriaid = categoriaRepository.lista_de_juego_por_categoria(categoria_name);
		List<Long> waaa = detalleJuegoRepository.lista_de_juego_por_categoria(categoriaid);
		List<Juego> weeee = new ArrayList<>();

		for (int i = 0; i < waaa.size(); i++) {
			weeee.add(juegoRepository.getById(waaa.get(i)));
		}
		return weeee;
	}

	@Transactional(readOnly = true)
	public List<Juego> findforGenero(String genero_name) {
		Long generoId = generoRepository.lista_de_juego_por_genero(genero_name);
		List<Long> waaa = generoJuegoRepository.lista_juego_genero(generoId);
		List<Juego> weeee = new ArrayList<>();

		for (int i = 0; i < waaa.size(); i++) {
			weeee.add(juegoRepository.getById(waaa.get(i)));
		}
		return weeee;
	}

	@Transactional(readOnly = true)
	public String url_Game(String nombre_videojuego) {
		return juegoRepository.gameurl(nombre_videojuego);
	}

	@Transactional(readOnly = true)
	public List<Juego> findforDeveloper(String developer_name) {
		Long developerId = developerRepository.lista_de_juego_por_developer(developer_name);
		List<Long> waaa = proyectoRepository.lista_proyecto(developerId);
		List<Juego> weeee = new ArrayList<>();

		for (int i = 0; i < waaa.size(); i++) {
			weeee.add(juegoRepository.getById(waaa.get(i)));
		}
		return weeee;
	}
}
