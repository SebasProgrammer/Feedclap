package com.example.easi641.services;

import java.util.ArrayList;
import java.util.List;

import com.example.easi641.common.DesarrolladorValidator;
import com.example.easi641.dto.DesarrolladorDto;
import com.example.easi641.dto.ProyectoDto;
import com.example.easi641.entities.Desarrollador;
import com.example.easi641.entities.Juego;
import com.example.easi641.entities.Proyecto;
import com.example.easi641.entities.User;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.DesarrolladorRepository;
import com.example.easi641.repository.JuegoRepository;
import com.example.easi641.repository.ProyectoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DesarrolladorService {
	private final JuegoRepository juegoRepository;

	private final ProyectoRepository proyectoRepository;

	private final DesarrolladorRepository desarrolladorRepository;

	public DesarrolladorService(DesarrolladorRepository desarrolladorRepository, ProyectoRepository proyectoRepository,
			JuegoRepository juegoRepository) {
		this.desarrolladorRepository = desarrolladorRepository;

		this.proyectoRepository = proyectoRepository;
		this.juegoRepository = juegoRepository;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Desarrollador createDesarrollador(DesarrolladorDto desarrolladorDto) {
		DesarrolladorValidator.validateDesarrollador(desarrolladorDto);
		Desarrollador desarrollador = Desarrollador.builder().nombre(desarrolladorDto.getNombre()).build();

		return desarrolladorRepository.save(desarrollador);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Desarrollador createDesarrollador(User user) {
		Desarrollador desarrollador = new Desarrollador();
		desarrollador.setId(user.getId());
		desarrollador.setNombre(user.getName());
		desarrollador.setProyectos(new ArrayList<>());
		return desarrolladorRepository.save(desarrollador);
	}

	@Transactional(readOnly = true)
	public List<Desarrollador> findAllDesarrolladores() {
		return desarrolladorRepository.findAll();
	}

	@Transactional
	public void deleteDesarrollador(Long desarrolladorId) {
		Desarrollador desarrollador = desarrolladorRepository.findById(desarrolladorId)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
		desarrolladorRepository.delete(desarrollador);
	}

	@Transactional
	public Proyecto createProyecto(ProyectoDto proyectoDto) {
		Desarrollador desarrollador = desarrolladorRepository.findById(proyectoDto.getDesarrolladorId())
				.orElseThrow(() -> new NotFoundException("Desarrollador not found."));

		Juego juego = juegoRepository.findById(proyectoDto.getJuegoId())
				.orElseThrow(() -> new NotFoundException("Juego not found."));

		Proyecto proyecto = new Proyecto();
		proyecto.setJuego(juego);
		proyecto.setDesarrollador(desarrollador);
		proyecto.setPuesto(proyectoDto.getPuesto());
		return proyectoRepository.save(proyecto);
	}
}
