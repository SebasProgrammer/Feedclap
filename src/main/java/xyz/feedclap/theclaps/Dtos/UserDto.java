package xyz.feedclap.theclaps.Dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDto {
	private String username;
	private String name;
	private String email;
	private String token;
	private LocalDate birthdate;
}
