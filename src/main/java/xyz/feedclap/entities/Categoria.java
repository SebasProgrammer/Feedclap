package xyz.feedclap.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name="id_categoria")
    private Integer id_categoria;

    @Column(name="nombre")
    private String nombre;

    ///////

    @ManyToOne
    private Videojuego videojuego_categoria;

}
