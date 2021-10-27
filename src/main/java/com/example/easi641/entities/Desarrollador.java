package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "desarrolladores")
public class Desarrollador {
	@Id
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@OneToMany(mappedBy = "desarrollador", cascade = { CascadeType.PERSIST,
			CascadeType.REMOVE }, fetch = FetchType.LAZY)

	private List<Proyecto> proyectos;

	@Column(name = "rating", nullable = false)
	private float rating;

}
