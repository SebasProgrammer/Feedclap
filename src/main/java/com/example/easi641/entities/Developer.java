package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "developers")
public class Developer {
	@Id
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "rating", nullable = false)
	private Float rating;

	@OneToMany(mappedBy = "developer", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<Project> projects;

	public Developer(User user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setProjects(new ArrayList<>());
	}

}
