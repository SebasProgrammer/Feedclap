package com.example.easi641.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.example.easi641.dto.RegisterDto;

import java.time.LocalDateTime;

@Entity
@Table(name = "registers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "developer_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "developer_reviewers_fk"))
	private Developer developer;

	@ManyToOne
	@JoinColumn(name = "reviewers_id", updatable = false, nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "reviewers_developer_fk"))
	private Reviewer reviewer;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "amount", nullable = false)
	private Double amount;

	@Column(name = "date", nullable = false)
	private LocalDateTime date;

	public Register(Developer developer, Reviewer reviewer, RegisterDto registerDto) {
		this.setDeveloper(developer);
		this.setReviewer(reviewer);
		this.setAmount(registerDto.getAmount());
		this.setDate(registerDto.getDate());
	}
}
