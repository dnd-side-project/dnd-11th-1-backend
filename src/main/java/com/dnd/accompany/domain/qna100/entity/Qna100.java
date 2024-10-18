package com.dnd.accompany.domain.qna100.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.dnd.accompany.domain.common.entity.TimeBaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "qna100s")
@SQLRestriction("deleted = false")
@SQLDelete(sql = "UPDATE qna100s SET deleted = true WHERE id = ?")
public class Qna100 extends TimeBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long userId;

	@Column(length = 2000)
	private String question;

	@Column(length = 2000)
	private String answer;

	private boolean deleted = false;

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public static Qna100 from(Long userId, String question, String answer){
		return Qna100.builder()
			.userId(userId)
			.question(question)
			.answer(answer)
			.build();
	}

	public static final String QUESTION1 = "고기 vs 해산물";
	public static final String QUESTION2 = "액티비티 vs 힐링";
	public static final String QUESTION3 = "낮 vs 밤 활동성이 많은 시간";
}
