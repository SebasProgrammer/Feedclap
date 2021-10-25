package com.example.easi641.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
	private String username;
	private String name;
	private String email;
	private String token;
	private int type; // dev rev
	private LocalDate birthdate;
}
