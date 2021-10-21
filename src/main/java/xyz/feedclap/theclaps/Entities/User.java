package xyz.feedclap.theclaps.Entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	private String username;
	private String name;
	private String email;
	private String token;
	private LocalDate birthdate;
	private int type;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Developer developer;
}
