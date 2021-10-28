package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "users_juegos_fk"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "juegos_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "juegos_users_fk"))
    private Juego juego;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "puntaje_review", nullable = false)
    private Integer puntaje;

    @Column(name = "valor_review", nullable = false)
    private Float valor;

    @Column(name = "estado_review", nullable = false)
    private String estado;
}
