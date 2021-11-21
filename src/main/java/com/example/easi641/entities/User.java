package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name="username",length = 30,nullable = false)
	private String username;
	private String name;
	private String email;
	@Column(name="password",length = 150,nullable = false)
	private String password;
	private int type;
	@Column(name = "nivel", nullable = false)
	private int nivel;
	@Column(name = "exp", nullable = false)
	private int exp;

	/*public User(UserDto userDto) {
		this.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
		this.setUsername(userDto.getUsername());
		this.setName(userDto.getName());
		this.setEmail(userDto.getEmail());
		//this.setToken(userDto.getToken());
		this.setBirthdate(userDto.getBirthdate());
		this.setType(userDto.getType());

	}

	public void updateFromDto(UserDto userDto) {
		this.setUsername(userDto.getUsername());
		this.setName(userDto.getName());
		this.setEmail(userDto.getEmail());
		//this.setToken(userDto.getToken());
	}*/
}
