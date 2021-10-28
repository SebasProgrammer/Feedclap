package com.example.easi641.controller;

import com.example.easi641.common.EntityDtoConverter;
import com.example.easi641.dto.JuegoDto;
import com.example.easi641.dto.ReviewDto;
import com.example.easi641.entities.Juego;
import com.example.easi641.entities.Review;
import com.example.easi641.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final EntityDtoConverter entityDtoConverter;

    public ReviewController(ReviewService reviewService, EntityDtoConverter entityDtoConverter) {
        this.reviewService = reviewService;
        this.entityDtoConverter = entityDtoConverter;
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewDto reviewDto){
        Review review = reviewService.createReview(reviewDto);
        return new ResponseEntity<>(entityDtoConverter.convertReviewToDto(review), HttpStatus.CREATED);
    }

    @GetMapping("/valor/{review_id}")
    public ResponseEntity<String> get_valor_review(@PathVariable Long review_id){
        String valor_review = reviewService.valor_reviewww(review_id);
        return new ResponseEntity<>(valor_review, HttpStatus.OK);
    }

    @DeleteMapping("/{review_id}")
    public ResponseEntity<String> popreview(@PathVariable Long review_id){
        reviewService.deleteReview(review_id);
        return new ResponseEntity<>("Se elimino correctamente",HttpStatus.OK);
    }
}
