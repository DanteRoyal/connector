package com.connector.api.domain.profile;

import java.time.LocalDate;

import com.connector.api.domain.profile.request.ExperienceRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String company;

	private String position;

	private String jobDescription;

	private LocalDate startDate;

	private LocalDate endDate;

	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;

	public void updateExperience(final ExperienceRequest request) {
		this.company = request.getCompany();
		this.position = request.getPosition();
		this.jobDescription = request.getJobDescription();
		this.startDate = request.getStartDate();
		this.endDate = request.getEndDate();
	}
}
