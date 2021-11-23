package com.example.easi641.services;

import java.util.ArrayList;
import java.util.List;

import com.example.easi641.common.UserValidator;
import com.example.easi641.dto.DeveloperDto;
import com.example.easi641.dto.ProjectDto;
import com.example.easi641.dto.RegisterDto;
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
	private RegisterRepository registerRepository;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private DeveloperRepository developerRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Developer createDeveloper(User user) {
		Developer developer = new Developer(user);
		developer.setRating(0.0f);
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
	public Project createProject(ProjectDto projectDto) {
		Developer developer = developerRepository.findById(projectDto.getDeveloperId())
				.orElseThrow(() -> new NotFoundException("Developer not found."));

		Game game = gameRepository.findById(projectDto.getGameId())
				.orElseThrow(() -> new NotFoundException("Game not found."));

		Project project = new Project(projectDto.getRole(), developer, game);
		return projectRepository.save(project);
	}

	@Transactional
	public Register createRegister(RegisterDto registerDto) {
		Developer developer = developerRepository.findById(registerDto.getDeveloperId())
				.orElseThrow(() -> new NotFoundException("Developer not found."));

		Reviewer reviewer = reviewerRepository.findById(registerDto.getReviewerId())
				.orElseThrow(() -> new NotFoundException("Reviewer not found."));

		Register register = new Register(developer, reviewer, registerDto);
		return registerRepository.save(register);
	}

	List<Developer> getDevelopersByIds(List<Long> developerIds) {
		List<Developer> developersArray = new ArrayList<>();
		for (Long developerId : developerIds) {
			developersArray.add(developerRepository.getById(developerId));
		}
		return developersArray;
	}

	@Transactional(readOnly = true)
	public List<Developer> findDevelopersByGame(Long gameId) {
		List<Long> developerIds = projectRepository.getDevelopersByGame(gameId);
		return getDevelopersByIds(developerIds);
	}

}
