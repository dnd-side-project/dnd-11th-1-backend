package com.dnd.accompany.domain.accompany.entity;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SoftDelete
@Table(name = "accompany_tag")
public class AccompanyTag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accompany_tag_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "accompany_board_id")
	private AccompanyBoard accompanyBoard;

	@Column(nullable = false)
	private String name;

	@Builder
	public AccompanyTag(Long id, AccompanyBoard accompanyBoard, String name) {
		this.id = id;
		this.accompanyBoard = accompanyBoard;
		this.name = name;
	}
}
