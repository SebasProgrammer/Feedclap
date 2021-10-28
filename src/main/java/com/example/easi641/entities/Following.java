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

	private Long follower;

	private Long following;

	public Following(User er, User ing) {
		follower = er.getId();
		following = ing.getId();
	}

}
