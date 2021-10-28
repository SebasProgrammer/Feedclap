package com.example.easi641.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "GeneroJuegos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneroJuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "generos_id",
            updatable = false,
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "generos_juegos_fk")
    )
    private Genero genero;

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
