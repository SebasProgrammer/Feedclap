package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "role", nullable = false)
	private String role;

	@ManyToOne
	@JoinColumn(name = "developer_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "developer_game_fk"))
	private Developer developer;

	@ManyToOne
	@JoinColumn(name = "game_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "game_developer_fk"))
	private Game game;

	public Project(String role, Developer developer, Game game) {
		this.setRole(role);
		this.setDeveloper(developer);
		this.setGame(game);
	}
}
