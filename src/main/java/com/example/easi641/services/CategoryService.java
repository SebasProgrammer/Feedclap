package com.example.easi641.services;

import com.example.easi641.common.CategoryValidator;
import com.example.easi641.dto.CategoryDto;
import com.example.easi641.dto.GameDetailDto;
import com.example.easi641.entities.Category;
import com.example.easi641.entities.GameDetail;
import com.example.easi641.entities.Game;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.CategoryRepository;
import com.example.easi641.repository.GameDetailRepository;
import com.example.easi641.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private GameDetailRepository gameDetailRepository;

	@Autowired
	private GameRepository gameRepository;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Category createCategory(CategoryDto categoryDto) {
		CategoryValidator.validateCategory(categoryDto);
		Category category = new Category();
		category.setName(categoryDto.getName());

		return categoryRepository.save(category);
	}

	@Transactional(readOnly = true)
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	@Transactional
	public GameDetail createGameDetail(GameDetailDto gameDetailDto) {
		Game game = gameRepository.findById(gameDetailDto.getGameId())
				.orElseThrow(() -> new NotFoundException("Game not found."));

		Category category = categoryRepository.findById(gameDetailDto.getCategoryId())
				.orElseThrow(() -> new NotFoundException("Category not found."));

		GameDetail gameDetail = new GameDetail(category, game);
		return gameDetailRepository.save(gameDetail);
	}
}
