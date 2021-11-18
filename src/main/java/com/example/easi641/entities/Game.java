package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "review_price", nullable = false)
	private Float reviewPrice;

	@Column(name = "download_link", nullable = false)
	private String downloadLink;

	@Column(name = "img_link", nullable = false)
	private String img_link;

	@OneToMany(mappedBy = "game", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<GameDetail> detailGames; // = new ArrayList<>();

	@OneToMany(mappedBy = "game", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<GameGenre> gameGenres; // = new ArrayList<>();
}
