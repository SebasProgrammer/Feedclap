package com.example.easi641.services;

import com.example.easi641.common.UserValidator;
import com.example.easi641.dto.ReviewDto;
import com.example.easi641.entities.*;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.JuegoRepository;
import com.example.easi641.repository.ReviewRepository;
import com.example.easi641.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Review createReview(ReviewDto reviewDto) {
        Review review=new Review();

        Juego juego = juegoRepository.findById(reviewDto.getJuegoId())
                .orElseThrow(() -> new NotFoundException("Game not found."));

        User user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found."));

        review.setJuego(juego);
        review.setUser(user);
        review.setDescripcion(reviewDto.getDescripcion());
        review.setPuntaje(reviewDto.getPuntaje());
        review.setValor(juego.getPrecio_feedback());
        review.setEstado(reviewDto.getEstado());
        return reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    public String valor_reviewww(Long review_id){
        Review review = reviewRepository.findById(review_id)
                .orElseThrow(()-> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
        Float va_re=review.getValor();
        return va_re.toString();
    }

    @Transactional
    public void deleteReview(Long review_id){
        Review review = reviewRepository.findById(review_id)
                .orElseThrow(()-> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
        reviewRepository.delete(review);
    }
}
