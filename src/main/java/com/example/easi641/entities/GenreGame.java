package com.example.easi641.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "GenreGames")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "genres_id",
            updatable = false,
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "genres_games_fk")
    )
    private Genre genre;

    @ManyToOne
    @JoinColumn(
            name = "juegos_id",
            updatable = false,
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "juegos_generos_fk")
    )
    private Juego juego;
}
