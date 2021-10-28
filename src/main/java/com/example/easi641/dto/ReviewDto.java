package com.example.easi641.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReviewDto {
    private Long id;
    private Long userId;
    private Long juegoId;
    @NotBlank
    @NotNull
    private String descripcion;
    @NotNull
    private Integer puntaje;
    @NotBlank
    @NotNull
    private String estado;
}
