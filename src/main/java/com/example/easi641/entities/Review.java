package com.example.easi641.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.example.easi641.dto.ReviewDto;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "reviewer", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "users_juegos_fk"))
	private User user;

	@ManyToOne
	@JoinColumn(name = "game", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "juegos_users_fk"))
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
		this.setCost(reviewDto.getCost());
		this.setState(reviewDto.getState());
	}
}
