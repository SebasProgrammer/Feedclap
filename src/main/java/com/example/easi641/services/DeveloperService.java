package com.example.easi641.services;

import java.util.ArrayList;
import java.util.List;

import com.example.easi641.common.UserValidator;
import com.example.easi641.dto.DeveloperDto;
import com.example.easi641.dto.ProyectoDto;
import com.example.easi641.dto.RegistroDto;
import com.example.easi641.entities.*;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeveloperService {
	@Autowired
	private ReviewerRepository reviewerRepository;

	@Autowired
	private RegistroRepository registroRepository;

	@Autowired
	private JuegoRepository juegoRepository;

	@Autowired
	private ProyectoRepository proyectoRepository;

	@Autowired
	private DeveloperRepository developerRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Developer createDeveloper(DeveloperDto developerDto) {
		UserValidator.validateDeveloper(developerDto);
		Developer developer = Developer.builder().name(developerDto.getName()).build();

		return developerRepository.save(developer);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Developer createDeveloper(User user) {
		Developer developer = new Developer();
		developer.setId(user.getId());
		developer.setName(user.getName());
		developer.setProyectos(new ArrayList<>());
		developer.setRating(2.5f);
		return developerRepository.save(developer);
	}

	@Transactional(readOnly = true)
	public List<User> findAllDeveloperes() {
		return userRepository.getDevelopers();
	}

	@Transactional
	public void deleteDeveloper(Long developerId) {
		Developer developer = developerRepository.findById(developerId)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
		developerRepository.delete(developer);
	}

	@Transactional
	public Proyecto createProyecto(ProyectoDto proyectoDto) {
		Developer developer = developerRepository.findById(proyectoDto.getDeveloperId())
				.orElseThrow(() -> new NotFoundException("Developer not found."));

		Juego juego = juegoRepository.findById(proyectoDto.getJuegoId())
				.orElseThrow(() -> new NotFoundException("Juego not found."));

		Proyecto proyecto = new Proyecto();
		proyecto.setJuego(juego);
		proyecto.setDeveloper(developer);
		proyecto.setPuesto(proyectoDto.getPuesto());
		return proyectoRepository.save(proyecto);
	}

	@Transactional
	public Registro createRegistro(RegistroDto registroDto) {
		Developer developer = developerRepository.findById(registroDto.getDeveloperId())
				.orElseThrow(() -> new NotFoundException("Developer not found."));

		Reviewer reviewer = reviewerRepository.findById(registroDto.getReviewerId())
				.orElseThrow(() -> new NotFoundException("Reviewer not found."));

		Registro registro = new Registro();
		registro.setDeveloper(developer);
		registro.setReviewer(reviewer);
		registro.setMonto(registroDto.getMonto());
		registro.setLocalDateTime(registroDto.getLocalDateTime());
		return registroRepository.save(registro);
	}

	@Transactional(readOnly = true)
	public List<Developer> findDeveloperName(String name_developer) {
		return developerRepository.nameDeveloper(name_developer);
	}

}
