package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "gamegenres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameGenre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "genre_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "genre_game_fk"))
	private Genre genre;

	@ManyToOne
	@JoinColumn(name = "game_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "game_genre_fk"))
	private Game game;

	public GameGenre(Genre genre, Game game) {
		this.setGenre(genre);
		this.setGame(game);
	}
}
