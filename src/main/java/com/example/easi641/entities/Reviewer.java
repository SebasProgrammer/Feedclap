package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviewers")
public class Reviewer {
	@Id
	@Column(name = "id", updatable = false)
	private Long id;

	@Builder.Default
	@Column(name = "nombre", nullable = false)
	private String nombre = "John Doe";

	@Column(name = "rating", nullable = false)
	private float rating;

	@Column(name = "nivel", nullable = false)
	private int nivel;

	@Column(name = "tipo", nullable = false)
	private int tipo;

	// List<Reviews>
	// @OneToMany(mappedBy = "desarrollador", cascade = { CascadeType.PERSIST,
	// CascadeType.REMOVE }, fetch = FetchType.LAZY)

	// private List<Proyecto> proyectos;
}
