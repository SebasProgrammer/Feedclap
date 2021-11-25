package com.example.easi641.repository;

import java.util.List;

import com.example.easi641.dto.ActivityReportDto;
import com.example.easi641.entities.Reviewer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {
	@Query(value = "SELECT r.id, r.date FROM reviews r WHERE r.reviewer=:reviewerId", nativeQuery = true)
	List<ActivityReportDto> getActivityReport(Long reviewerId);
}
