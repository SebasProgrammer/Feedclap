package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "proyectos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "puesto", nullable = false)
	private String puesto;

	@ManyToOne
	@JoinColumn(name = "desarrolladores_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "desarrolladores_juegos_fk"))
	private Desarrollador desarrollador;

	@ManyToOne
	@JoinColumn(name = "juegos_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "juegos_desarrolladores_fk"))
	private Juego juego;
}
