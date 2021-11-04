package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.example.easi641.dto.UserDto;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	private String username;
	private String name;
	private String email;
	private String token;
	private LocalDate birthdate;
	private int type;

	public User(UserDto userDto) {
		this.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
		this.setUsername(userDto.getUsername());
		this.setName(userDto.getName());
		this.setEmail(userDto.getEmail());
		this.setToken(userDto.getToken());
		this.setBirthdate(userDto.getBirthdate());
		this.setType(userDto.getType());

	}

	public void updateFromDto(UserDto userDto) {
		this.setUsername(userDto.getUsername());
		this.setName(userDto.getName());
		this.setEmail(userDto.getEmail());
		this.setToken(userDto.getToken());
	}
}
