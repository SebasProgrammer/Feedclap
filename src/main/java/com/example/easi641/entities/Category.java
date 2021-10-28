package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
<<<<<<< HEAD:src/main/java/com/example/easi641/entities/Category.java
@Table(
        name="categories"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name="id", updatable = false)
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "VARCHAR(50)",
            length = 50
    )
    private String name;

    @OneToMany(
            mappedBy = "category",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<DetailGame> detailGames = new ArrayList<>();
=======
@Table(name = "categorias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "nombre", nullable = false, columnDefinition = "VARCHAR(50)", length = 50)
	private String nombre;

	@OneToMany(mappedBy = "categoria", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<DetalleJuego> detalleJuegos = new ArrayList<>();
>>>>>>> ae1c57804cb922af8ed60bbc2c0852a546bdc72f:src/main/java/com/example/easi641/entities/Categoria.java
}
