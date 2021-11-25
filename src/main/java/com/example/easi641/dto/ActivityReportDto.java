package com.example.easi641.dto;

import lombok.Data;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ActivityReportDto {
	private Long id;
	@NotBlank
	@NotNull
	private LocalDate date;
}
