package com.example.easi641.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDto {
	private Long id;
	private String username;
	//private String name;
	//private String email;
	//private String token;
	//private int type; // dev rev
	//private LocalDate birthdate;
}
