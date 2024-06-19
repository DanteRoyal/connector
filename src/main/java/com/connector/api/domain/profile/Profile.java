package com.connector.api.domain.profile;

import java.util.ArrayList;
import java.util.List;

import com.connector.api.domain.BaseTimeEntity;
import com.connector.api.domain.profile.request.ProfileRequest;
import com.connector.api.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Profile extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private ProfessionalStatus professionalStatus;

	private String nickname;

	private String company;

	private String introduction;

	private String website;

	private String techStacks;

	// @OneToOne
	// private ProfileImage profileImage;

	@OneToMany(mappedBy = "profile")
	private List<Education> educations = new ArrayList<>();

	@OneToMany(mappedBy = "profile")
	private List<Experience> experiences = new ArrayList<>();

	@OneToOne
	private User user;

	public void updateProfile(final ProfileRequest request) {
		this.professionalStatus = request.getProfessionalStatus();
		this.nickname = request.getNickname();
		this.company = request.getCompany();
		this.introduction = request.getIntroduction();
		this.website = request.getWebsite();
		this.techStacks = request.getTechStacks();
	}
}
