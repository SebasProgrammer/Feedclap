package xyz.feedclap.theclaps.Entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
public class Developer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long developerId;
	private int rating;

	// List<Videogame> videogames
	// List<User> followers

	@ManyToOne
	@JoinColumn(name = "id")
	private User user_id;
}
