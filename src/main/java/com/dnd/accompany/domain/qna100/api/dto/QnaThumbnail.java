package com.dnd.accompany.domain.qna100.api.dto;

import com.dnd.accompany.domain.qna100.entity.Qna100;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class QnaThumbnail extends FindSlicesResult {
	private Long id;
	private String question;
	private String answer;

	public QnaThumbnail(Long id, String question, String answer) {
		super(id);

		this.id = id;
		this.question = question;
		this.answer = answer;
	}

	public QnaThumbnail fromEntity(Qna100 qna100) {
		return QnaThumbnail.builder()
			.id(qna100.getId())
			.question(qna100.getQuestion())
			.answer(qna100.getAnswer())
			.build();
	}
}
