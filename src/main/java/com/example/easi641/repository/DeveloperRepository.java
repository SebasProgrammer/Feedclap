package com.example.easi641.repository;

import java.util.List;

import com.example.easi641.dto.ActivityReportDto;
import com.example.easi641.entities.Developer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
	@Query(value = "SELECT d.id FROM developers d WHERE d.name=:developerName", nativeQuery = true)
	Long getDeveloperId(String developerName);

	@Query(value = "SELECT g.id, g.date FROM games g WHERE g.developer_id=:developerId", nativeQuery = true)
	List<ActivityReportDto> getActivityReport(Long developerId);
}
