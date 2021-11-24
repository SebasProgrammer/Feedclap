package com.example.easi641.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
//
@AllArgsConstructor
@NoArgsConstructor
//
@Builder
public class UserDto {
	private Long id;
	private String username;
	private String name;
	private String email;
	private int type; // dev rev
	private int nivel;
	private int exp;
	private String rank;
	private String information;
}
