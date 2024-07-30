package com.dnd.accompany.domain.accompany.entity;

import org.hibernate.annotations.SoftDelete;

import com.dnd.accompany.domain.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "accompany_users")
public class AccompanyUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accompany_users_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "accompany_board_id")
	private AccompanyBoard accompanyBoard;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	public enum Role {
		HOST, APPLICANT, PARTICIPANT
	}

	@Builder
	public AccompanyUsers(Long id, User user, AccompanyBoard accompanyBoard, Role role) {
		this.id = id;
		this.user = user;
		this.accompanyBoard = accompanyBoard;
		this.role = role;
	}
}
