package com.dnd.accompany.domain.accompany.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
@Table(name = "accompany_images", indexes = {
	@Index(name = "IX_accompany_images_id", columnList = "accompany_images_id"),
	@Index(name = "IX_accompany_boards_id", columnList = "accompany_boards_id")
})
@SQLRestriction("deleted = false")
@SQLDelete(sql = "UPDATE t_order SET deleted = true WHERE id = ?")
public class AccompanyImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accompany_images_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accompany_boards_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private AccompanyBoards accompanyBoards;

	@Column(nullable = false, length = 2000)
	private String imageUrl;

	@Builder
	public AccompanyImages(Long id, AccompanyBoards accompanyBoards, String imageUrl) {
		this.id = id;
		this.accompanyBoards = accompanyBoards;
		this.imageUrl = imageUrl;
	}
}