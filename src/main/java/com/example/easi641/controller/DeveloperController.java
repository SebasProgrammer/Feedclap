package com.example.easi641.controller;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.*;
import com.example.easi641.entities.*;
import com.example.easi641.services.DeveloperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

	private final EntityDtoConverter entityDtoConverter;

	private final DeveloperService developerService;

	public DeveloperController(DeveloperService developerService, EntityDtoConverter entityDtoConverter) {
		this.developerService = developerService;
		this.entityDtoConverter = entityDtoConverter;
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> findAllDeveloperes() {
		List<User> developers = developerService.findAllDeveloperes();
		return new ResponseEntity<>(entityDtoConverter.convertUserToDto(developers), HttpStatus.OK);
	}

	// @PutMapping("/{developerId}")
	// public ResponseEntity<JuegoResponse> popGame(@PathVariable Long
	// developerId) {
	// developerService.deleteDeveloper(developerId);
	// return new ResponseEntity<>(HttpStatus.OK);
	// }

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/proyecto")
	public ResponseEntity<ProyectoDto> createProyecto(@Valid @RequestBody ProyectoDto proyectoDto) {
		Proyecto proyecto = developerService.createProyecto(proyectoDto);
		return new ResponseEntity<>(entityDtoConverter.convertProyectoToDto(proyecto), HttpStatus.CREATED);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/registro")
	public ResponseEntity<RegistroDto> createRegistro(@Valid @RequestBody RegistroDto registroDto) {
		Registro registro = developerService.createRegistro(registroDto);
		return new ResponseEntity<>(entityDtoConverter.convertRegistroToDto(registro), HttpStatus.CREATED);
	}

	@GetMapping("/nombres/{name_game}")
	public ResponseEntity<List<DeveloperDto>> findDeveloperNames(@PathVariable String name_game) {
		List<Developer> developers = developerService.findDeveloperName(name_game);
		return new ResponseEntity<>(entityDtoConverter.convertDeveloperToDto(developers), HttpStatus.OK);

	}
}
