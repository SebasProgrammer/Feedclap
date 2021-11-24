package com.example.easi641.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "following")
@Data
public class Following {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "follower")
	private String follower;

	@Column(name = "following")
	private String following;

	public Following(User er, User ing) {
		this.setFollower(er.getUsername());
		this.setFollowing(ing.getUsername());
	}

}
