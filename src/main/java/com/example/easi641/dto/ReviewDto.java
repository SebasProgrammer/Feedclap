package com.example.easi641.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReviewDto {
    private Long userId;
    private Long juegoId;
    @NotBlank
    @NotNull
    private String descripcion;
    @NotBlank
    @NotNull
    private Integer puntaje;
}
