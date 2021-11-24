package com.example.easi641.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReviewDto {
	private Long id;
	private Long userId;
	private Long gameId;
	@NotBlank
	@NotNull
	private String description;
	@NotNull
	private Integer rating;
	@NotBlank
	@NotNull
	private String state;
	private Float cost;
}
