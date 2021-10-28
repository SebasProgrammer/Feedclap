package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Detallesjuego")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleJuego {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "categorias_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "categorias_juegos_fk"))
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "juegos_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "juegos_categorias_fk"))
	private Juego juego;
}
