package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name="Detallesjuego"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailGame {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name="id",
            updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "categories_id",
            updatable = false,
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "categories_games_fk"
            )
    )
    private Category category;

    @ManyToOne
    @JoinColumn(
            name = "games_id",
            updatable = false,
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "juegos_categories_fk"
            )
    )
    private Juego juego;
}
