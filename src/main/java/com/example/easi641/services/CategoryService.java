package com.example.easi641.services;

import com.example.easi641.common.CategoryValidator;
import com.example.easi641.dto.CategoryDto;
import com.example.easi641.dto.DetailGameDto;
import com.example.easi641.entities.Category;
import com.example.easi641.entities.DetailGame;
import com.example.easi641.entities.Juego;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.CategoryRepository;
import com.example.easi641.repository.DetalleJuegoRepository;
import com.example.easi641.repository.JuegoRepository;
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
    private DetalleJuegoRepository detalleJuegoRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Category createCategory(CategoryDto categoryDto){
        CategoryValidator.validateCategory(categoryDto);
        Category category = new Category();
        category.setName(categoryDto.getName());

        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }


    @Transactional
    public DetailGame createDetailGame(DetailGameDto detailGameDto) {
        Juego game = juegoRepository.findById(detailGameDto.getJuegoId())
                .orElseThrow(() -> new NotFoundException("Game not found."));

        Category category = categoryRepository.findById(detailGameDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found."));

        DetailGame detailGame = new DetailGame();
        detailGame.setJuego(game);
        detailGame.setCategory(category);
        return detalleJuegoRepository.save(detailGame);
    }
}
