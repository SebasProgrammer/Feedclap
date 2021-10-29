package com.example.easi641.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProjectDto {
	private Long developerId;
	private Long gameId;
	@NotBlank
	@NotNull
	private String role;
}
