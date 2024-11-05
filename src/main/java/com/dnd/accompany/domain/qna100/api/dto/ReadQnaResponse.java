package com.dnd.accompany.domain.qna100.api.dto;

import static java.util.stream.Collectors.*;

import java.util.List;

import com.dnd.accompany.domain.qna100.entity.Qna100;

public record ReadQnaResponse(
	List<Qna> qnas
) {

	public static ReadQnaResponse from(List<Qna100> qna100s) {
		return new ReadQnaResponse(qna100s.stream()
			.map(Qna::from)
			.collect(toList()));
	}
}
