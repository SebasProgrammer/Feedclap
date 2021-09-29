package xyz.feedclap.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Videojuegos")
public class Videojuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name="id_videojuego")
    private Integer id_videojuego;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="autor")
    private String autor;

    @Column(name="calificacion")
    private Integer calificacion;

    @Column(name="link_de_descarga")
    private String link_de_descarga;

    @Column(name="restricciones")
    private String restricciones;

    /////////

    @OneToMany(mappedBy = "videojuego_categoria")
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "videojuego_genero")
    private List<Género> generos;


    @OneToMany(mappedBy = "videojuego_plataforma")
    private List<Plataforma> plataformas;

    /////////

    @ManyToOne
    private Videojuego desarrollador_videojuego;

}
