package com.example.easi641.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RegisterDto {
	private Long developerId;
	private Long reviewerId;
	@NotBlank
	@NotNull
	private String description;

	@NotBlank
	@NotNull
	private Double amount;

	@NotBlank
	@NotNull
	private LocalDateTime date;

}
