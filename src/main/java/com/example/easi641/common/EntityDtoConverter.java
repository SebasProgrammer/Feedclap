package com.example.easi641.common;

import com.example.easi641.dto.*;
import com.example.easi641.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityDtoConverter {
	private final ModelMapper modelMapper;

	public EntityDtoConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public JuegoDto convertJuegoToDto(Juego juego) {
		return modelMapper.map(juego, JuegoDto.class);
	}

	// public Juego convertEntityToDto(JuegoRequest juegoRequest){
	// return modelMapper.map(juegoRequest, Juego.class);
	// }

	public List<JuegoDto> convertJuegoToDto(List<Juego> juegos) {
		return juegos.stream().map(this::convertJuegoToDto) // igual a this::converEntutyToDto
				.collect(Collectors.toList());
	}

	public CategoriaDto convertCategoriaToDto(Categoria categoria) {
		return modelMapper.map(categoria, CategoriaDto.class);
	}

	public List<CategoriaDto> convertCategoriaToDto(List<Categoria> canciones) {
		return canciones.stream().map(this::convertCategoriaToDto) // igual a this::converEntutyToDto
				.collect(Collectors.toList());
	}

	public DetalleJuegoDto convertDetalleJuegoToDto(DetalleJuego detalleJuego) {
		return modelMapper.map(detalleJuego, DetalleJuegoDto.class);
	}

	public DesarrolladorDto convertDesarrolladorToDto(Desarrollador desarrollador) {
		return modelMapper.map(desarrollador, DesarrolladorDto.class);
	}

	public ReviewerDto convertReviewerToDto(Reviewer reviewer) {
		return modelMapper.map(reviewer, ReviewerDto.class);
	}

	public List<DesarrolladorDto> convertDesarrolladorToDto(List<Desarrollador> desarrolladores) {
		return desarrolladores.stream().map(this::convertDesarrolladorToDto).collect(Collectors.toList());
	}

	public List<ReviewerDto> convertReviewerToDto(List<Reviewer> reviewers) {
		return reviewers.stream().map(this::convertReviewerToDto).collect(Collectors.toList());
	}

	public ProyectoDto convertProyectoToDto(Proyecto proyecto) {
		return modelMapper.map(proyecto, ProyectoDto.class);
	}

	public List<ProyectoDto> convertProyectoToDto(List<Proyecto> proyectos) {
		return proyectos.stream().map(this::convertProyectoToDto).collect(Collectors.toList());
	}

	public UserDto convertUserToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	public List<UserDto> convertUserToDto(List<User> users) {
		return users.stream()// Stream<User>
				.map(this::convertUserToDto)// Stream<UserDto>
				.collect(Collectors.toList());// List<UserDto>
	}
}
