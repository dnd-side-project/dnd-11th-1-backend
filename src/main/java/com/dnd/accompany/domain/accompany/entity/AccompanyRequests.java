package com.dnd.accompany.domain.accompany.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.SoftDelete;

import com.dnd.accompany.domain.user.entity.User;

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
@Table(name = "accompany_requests")
public class AccompanyRequests {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accompany_requests_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "accompany_board_id")
	private AccompanyBoard accompanyBoard;

	@Column(nullable = false)
	private LocalDateTime applicationDate;
	private LocalDateTime approvedDate;

	@Builder
	public AccompanyRequests(Long id, User user, AccompanyBoard accompanyBoard, LocalDateTime applicationDate,
		LocalDateTime approvedDate) {
		this.id = id;
		this.user = user;
		this.accompanyBoard = accompanyBoard;
		this.applicationDate = applicationDate;
		this.approvedDate = approvedDate;
	}
}
