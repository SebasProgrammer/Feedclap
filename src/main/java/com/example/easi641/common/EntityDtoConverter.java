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

	public GameDto convertGameToDto(Game game) {
		return modelMapper.map(game, GameDto.class);
	}

	// public Juego convertEntityToDto(JuegoRequest juegoRequest){
	// return modelMapper.map(juegoRequest, Juego.class);
	// }

	public List<GameDto> convertGameToDto(List<Game> games) {
		return games.stream().map(this::convertGameToDto) // igual a this::converEntutyToDto
				.collect(Collectors.toList());
	}

	public CategoryDto convertCategoryToDto(Category category) {
		return modelMapper.map(category, CategoryDto.class);
	}

	public List<CategoryDto> convertCategoryToDto(List<Category> categories) {
		return categories.stream().map(this::convertCategoryToDto) // igual a this::converEntutyToDto
				.collect(Collectors.toList());
	}

	public GameDetailDto convertDetalleJuegoToDto(GameDetail gameDetail) {
		return modelMapper.map(gameDetail, GameDetailDto.class);
	}

	public DeveloperDto convertDeveloperToDto(Developer developer) {
		return modelMapper.map(developer, DeveloperDto.class);
	}

	public ReviewerDto convertReviewerToDto(Reviewer reviewer) {
		return modelMapper.map(reviewer, ReviewerDto.class);
	}

	public ReviewDto convertReviewerToDto(Review review) {
		return modelMapper.map(review, ReviewDto.class);
	}

	public List<DeveloperDto> convertDeveloperToDto(List<Developer> developers) {
		return developers.stream().map(this::convertDeveloperToDto).collect(Collectors.toList());
	}

	public List<ReviewerDto> convertReviewerToDto(List<Reviewer> reviewers) {
		return reviewers.stream().map(this::convertReviewerToDto).collect(Collectors.toList());
	}

	public ProjectDto convertProjectToDto(Project project) {
		return modelMapper.map(project, ProjectDto.class);
	}

	public List<ProjectDto> convertProjectToDto(List<Project> projects) {
		return projects.stream().map(this::convertProjectToDto).collect(Collectors.toList());
	}

	public UserDto convertUserToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	public List<UserDto> convertUserToDto(List<User> users) {
		return users.stream()// Stream<User>
				.map(this::convertUserToDto)// Stream<UserDto>
				.collect(Collectors.toList());// List<UserDto>
	}

	public ReviewDto convertReviewToDto(Review review) {
		return modelMapper.map(review, ReviewDto.class);
	}

	public List<ReviewDto> convertReviewToDto(List<Review> reviews) {
		return reviews.stream().map(this::convertReviewToDto).collect(Collectors.toList());
	}

	public RegisterDto convertRegisterToDto(Register register) {
		return modelMapper.map(register, RegisterDto.class);
	}

	public List<RegisterDto> convertRegisterToDto(List<Register> registers) {
		return registers.stream().map(this::convertRegisterToDto).collect(Collectors.toList());
	}

	public GenreDto convertGenreToDto(Genre genre) {
		return modelMapper.map(genre, GenreDto.class);
	}

	public List<GenreDto> convertGenreToDto(List<Genre> genres) {
		return genres.stream().map(this::convertGenreToDto).collect(Collectors.toList());
	}

	public GameGenreDto convertGameGenreToDto(GameGenre gameGenre) {
		return modelMapper.map(gameGenre, GameGenreDto.class);
	}

	public List<GameGenreDto> convertGameGenreToDto(List<GameGenre> gameGenres) {
		return gameGenres.stream().map(this::convertGameGenreToDto).collect(Collectors.toList());
	}
}
