package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reviewers")
public class Reviewer {
	@Id
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "rating", nullable = false)
	private float rating;

	// List<Reviews>
	// @OneToMany(mappedBy = "desarrollador", cascade = { CascadeType.PERSIST,
	// CascadeType.REMOVE }, fetch = FetchType.LAZY)

	// private List<Proyecto> proyectos;
}
