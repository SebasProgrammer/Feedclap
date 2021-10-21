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
@Table(name = "reviewers")
@NoArgsConstructor
public class Reviewer {
	@Id
	@Column(name = "user_id")
	private Long id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private User user;

	private int type; // 1: Mortal, 2: God
	private int level;
	private int rating;
}
