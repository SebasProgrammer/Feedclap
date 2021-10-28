package com.example.easi641.controller;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.*;
import com.example.easi641.entities.*;
import com.example.easi641.services.DesarrolladorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/desarrolladores")
public class DesarrolladorController {

	private final EntityDtoConverter entityDtoConverter;

	private final DesarrolladorService desarrolladorService;

	public DesarrolladorController(DesarrolladorService desarrolladorService, EntityDtoConverter entityDtoConverter) {
		this.desarrolladorService = desarrolladorService;
		this.entityDtoConverter = entityDtoConverter;
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> findAllDesarrolladores() {
		List<User> desarrolladores = desarrolladorService.findAllDesarrolladores();
		return new ResponseEntity<>(entityDtoConverter.convertUserToDto(desarrolladores), HttpStatus.OK);
	}

	// @PutMapping("/{desarrolladorId}")
	// public ResponseEntity<JuegoResponse> popGame(@PathVariable Long
	// desarrolladorId) {
	// desarrolladorService.deleteDesarrollador(desarrolladorId);
	// return new ResponseEntity<>(HttpStatus.OK);
	// }

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/proyecto")
	public ResponseEntity<ProyectoDto> createProyecto(@Valid @RequestBody ProyectoDto proyectoDto) {
		Proyecto proyecto = desarrolladorService.createProyecto(proyectoDto);
		return new ResponseEntity<>(entityDtoConverter.convertProyectoToDto(proyecto), HttpStatus.CREATED);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/registro")
	public ResponseEntity<RegistroDto> createRegistro(@Valid @RequestBody RegistroDto registroDto) {
		Registro registro = desarrolladorService.createRegistro(registroDto);
		return new ResponseEntity<>(entityDtoConverter.convertRegistroToDto(registro), HttpStatus.CREATED);
	}

	@GetMapping("/nombres/{name_game}")
	public ResponseEntity<List<DesarrolladorDto>> findDesarrolladoresNames(@PathVariable String name_game){
		List<Desarrollador> desarrolladores = desarrolladorService.findDesarrolladorName(name_game);
		return new ResponseEntity<>(entityDtoConverter.convertDesarrolladorToDto(desarrolladores), HttpStatus.OK);

	}

}
