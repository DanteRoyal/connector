package com.connector.api.domain.profile;

import com.connector.api.domain.BaseTimeEntity;
import com.connector.api.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Profile extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nickname;

	private String introduction;

	@OneToOne
	private ProfileImage profileImage;

	@OneToOne
	private User user;

	private String website;

}
