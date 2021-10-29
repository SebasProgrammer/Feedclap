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

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/project") // TODO Project Dto must ask for a username and not an ID
	public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto projectDto) {
		Project project = developerService.createProject(projectDto);
		return new ResponseEntity<>(entityDtoConverter.convertProjectToDto(project), HttpStatus.CREATED);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/register")
	public ResponseEntity<RegisterDto> createRegister(@Valid @RequestBody RegisterDto registerDto) {
		Register register = developerService.createRegister(registerDto);
		return new ResponseEntity<>(entityDtoConverter.convertRegisterToDto(register), HttpStatus.CREATED);
	}

	@GetMapping("/bygame/{name_game}") // TODO Not working at all
	public ResponseEntity<List<DeveloperDto>> findDeveloperNames(@PathVariable Long gameId) {
		List<Developer> developers = developerService.findDevelopersByGame(gameId);
		return new ResponseEntity<>(entityDtoConverter.convertDeveloperToDto(developers), HttpStatus.OK);
	}
}
