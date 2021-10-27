package com.example.easi641.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "name", nullable = false)
	private String name = "John Doe";

	@Column(name = "email", nullable = false)
	private String email = "";

	@Column(name = "token", nullable = false)
	private String token;

	@Column(name = "birthdate", nullable = false)
	private LocalDate birthdate = LocalDate.now();

	@Column(name = "type", nullable = false)
	private int type = 0;
}
