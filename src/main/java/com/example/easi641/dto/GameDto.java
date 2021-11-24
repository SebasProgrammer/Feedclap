package com.example.easi641.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class GameDto {
	private Long id;
	@NotBlank
	@NotNull
	private String name;
	@NotBlank
	@NotNull
	private String description;
	@NotNull
	private Float reviewPrice;
	@NotBlank
	@NotNull
	private String downloadLink;
	@NotBlank
	@NotNull
	private String img_link;
	private Long developerId;
}
