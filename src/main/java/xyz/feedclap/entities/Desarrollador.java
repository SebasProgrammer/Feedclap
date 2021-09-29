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
@Table(name="Desarrolladores")
public class Desarrollador{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name="id_desarrollador")
    private Integer id_desarrollador;

    @Column(name="cant_seguidores")
    private Integer cant_seguidores;

    @Column(name="calificación")
    private Integer calificacion;

    ////////////////////

    @OneToMany(mappedBy = "desarrollador_videojuego")
    private List<Videojuego> lista_videojuegos;


}
