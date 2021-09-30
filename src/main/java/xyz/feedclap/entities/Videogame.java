package xyz.feedclap.entities;

//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="Videogame")
public class Videogame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="description")
    private String description;

    @Column(name="author")
    private String author;

    @Column(name="rating")
    private Integer rating;

    @Column(name="downloadLink")
    private String downloadLink;

    @Column(name="restriction")
    private String restriction;

    /////////

    // @OneToMany(mappedBy = "videojuego_categoria")
    // private List<Categoria> categorias;

    // @OneToMany(mappedBy = "videojuego_genero")
    // private List<Genero> generos;


    // @OneToMany(mappedBy = "videojuego_plataforma")
    // private List<Plataforma> plataformas;

    /////////
}
