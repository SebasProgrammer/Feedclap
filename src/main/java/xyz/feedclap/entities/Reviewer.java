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
@Table(name="Reviewers")
public class Reviewer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name="id_reviewer")
    private Integer id_reviewer;

    @Column(name="tipo_reviewer")
    private Integer tipo_reviewer;

    @Column(name="nivel")
    private Integer nivel;

    @Column(name="puntos")
    private Integer puntos;

    /////////

    @OneToMany(mappedBy = "reviewer_review")
    private List<Review> lista_reviews;
}
