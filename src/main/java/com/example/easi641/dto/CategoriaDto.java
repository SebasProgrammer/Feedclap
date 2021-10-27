package com.example.easi641.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoriaDto {
    private Long id;
    @NotBlank
    @NotNull
    private String nombre;
}
