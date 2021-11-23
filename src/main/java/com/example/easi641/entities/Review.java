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

import com.example.easi641.dto.ReviewDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "reviewer", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "users_games_fk"))
	private User user;

	@ManyToOne
	@JoinColumn(name = "game", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "games_users_fk"))
	private Game game;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "rating", nullable = false)
	private Integer rating;

	@Column(name = "cost", nullable = false)
	private Float cost;

	@Column(name = "state", nullable = false)
	private String state;

	public Review(User user, Game game, ReviewDto reviewDto) {
		this.setGame(game);
		this.setUser(user);
		this.setDescription(reviewDto.getDescription());
		this.setRating(reviewDto.getRating());
		this.setState(reviewDto.getState());
	}
}
