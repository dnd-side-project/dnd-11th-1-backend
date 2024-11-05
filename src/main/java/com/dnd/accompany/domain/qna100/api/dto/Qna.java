package com.dnd.accompany.domain.qna100.api.dto;

import com.dnd.accompany.domain.qna100.entity.Qna100;

import jakarta.validation.constraints.Size;

public record Qna(
	Long id,
	@Size(max = 2000) String question,
	@Size(max = 2000) String answer
) {

	public static Qna from(Qna100 qna100) {
		return new Qna(qna100.getId(), qna100.getQuestion(), qna100.getAnswer());
	}
}
