package com.dnd.accompany.domain.accompany.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.SoftDelete;

import com.dnd.accompany.domain.common.entity.TimeBaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SoftDelete
@Table(name = "accompany_board")
public class AccompanyBoard extends TimeBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accompany_board_id")
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(
		length = 1000,
		nullable = false
	)
	private String content;

	@OneToMany(mappedBy = "accompanyBoard", cascade = CascadeType.ALL)
	private List<AccompanyTag> accompanyTags;

	@OneToMany(mappedBy = "accompanyBoard", cascade = CascadeType.ALL)
	private List<AccompanyImages> accompanyImages;

	@Column(nullable = false)
	private String region;

	@Column(nullable = false)
	private String district;

	@Column(nullable = false)
	private LocalDateTime startDate;

	@Column(nullable = false)
	private LocalDateTime endDate;

	@Column(nullable = false)
	private Long currentPeopleNum;

	@Column(nullable = false)
	private Long targetPeopleNum;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Category category;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Age age;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;

	public enum Age {
		SAME, TWENTIES, THIRTIES, FORTIES, FIFTIES, SIXTIES, ANY
	}

	public enum Category {
		FULL, PART, LODGING, TOUR, MEAL
	}

	public enum Gender {
		SAME, DIFFERENT, ANY
	}

	@Builder
	public AccompanyBoard(Long id, String title, String content, List<AccompanyTag> accompanyTags,
		List<AccompanyImages> accompanyImages, String region, String district, LocalDateTime startDate,
		LocalDateTime endDate, Long currentPeopleNum, Long targetPeopleNum, Category category, Age age, Gender gender) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.accompanyTags = accompanyTags;
		this.accompanyImages = accompanyImages;
		this.region = region;
		this.district = district;
		this.startDate = startDate;
		this.endDate = endDate;
		this.currentPeopleNum = currentPeopleNum;
		this.targetPeopleNum = targetPeopleNum;
		this.category = category;
		this.age = age;
		this.gender = gender;
	}
}
