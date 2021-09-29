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
@Table(name="Reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name="id_review")
    private Integer id_review;

    @Column(name="contenido")
    private String contenido;

    @Column(name="calificación")
    private Integer calificación;

    /////

    @ManyToOne
    private Reviewer reviewer_review;

}
