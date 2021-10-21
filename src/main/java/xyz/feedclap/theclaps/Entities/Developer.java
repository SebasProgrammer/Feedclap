package xyz.feedclap.theclaps.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "developers")
@NoArgsConstructor
public class Developer {
	@Id
	@Column(name = "user_id")
	private Long id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private User user;

	private int rating;
	// @TODO
	// followers list reference
	// videogame list reference
}
