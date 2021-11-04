package com.example.easi641.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gamedetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "category_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "categories_games_fk"))
	private Category category;

	@ManyToOne
	@JoinColumn(name = "game_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "games_categories_fk"))
	private Game game;

	public GameDetail(Category category, Game game) {
		this.setGame(game);
		this.setCategory(category);
	}
}
