package xyz.feedclap.entities;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Reviewer")
public class Reviewer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="followers")
    private Integer followers;

    @Column(name="puntos")
    private Integer puntos;

    @Column(name="nivel")
    private Integer nivel;

    @Column(name="tipoReviewer")
    private Integer tipoReviewer;

    ////////////////////

    @OneToMany(mappedBy = "Review")
    private List<Review> reviewList;
}