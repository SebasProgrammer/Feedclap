package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
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

	@Length(max = 2048)
	@Column(name = "img_link", nullable = false)
	private String img_link;

	@Column(name = "developer_id", nullable = false)
	private Long developer_id;

	@Column(name = "date", nullable = false)
	private LocalDate date;

	@OneToMany(mappedBy = "game", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<GameDetail> detailGames; // = new ArrayList<>();

	@OneToMany(mappedBy = "game", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<GameGenre> gameGenres; // = new ArrayList<>();
}
