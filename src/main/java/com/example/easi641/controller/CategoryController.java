package com.example.easi641.controller;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.*;
import com.example.easi641.dto.DetailGameDto;
import com.example.easi641.entities.Category;
import com.example.easi641.entities.DetailGame;
import com.example.easi641.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    private final EntityDtoConverter entityDtoConverter;

    public CategoryController(CategoryService categoryService, EntityDtoConverter entityDtoConverter) {
        this.categoryService = categoryService;
        this.entityDtoConverter = entityDtoConverter;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategories(@Valid @RequestBody CategoryDto categoryDto){
        Category category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(entityDtoConverter.convertCategoryToDto(category), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAllCategories(){
        List<Category> categories = categoryService.findAllCategories();
        return new ResponseEntity<>(entityDtoConverter.convertCategoryToDto(categories), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/detailGame")
    public ResponseEntity<DetailGameDto> createDetailGame(@Valid @RequestBody DetailGameDto detailGameDto){
        DetailGame detailGame = categoryService.createDetailGame(detailGameDto);
        return new ResponseEntity<>(entityDtoConverter.convertDetalleJuegoToDto(detailGame), HttpStatus.CREATED);
    }

}
