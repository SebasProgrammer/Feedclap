package com.example.easi641.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RegistroDto {
	private Long developerId;
	private Long reviewerId;
	@NotBlank
	@NotNull
	private String descripcion;

	@NotBlank
	@NotNull
	private Double monto;

	@NotBlank
	@NotNull
	private LocalDateTime localDateTime;

}
