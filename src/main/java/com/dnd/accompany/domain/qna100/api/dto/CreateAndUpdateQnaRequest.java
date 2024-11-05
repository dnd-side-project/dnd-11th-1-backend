package com.dnd.accompany.domain.qna100.api.dto;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import com.dnd.accompany.domain.qna100.entity.Qna100;

public record CreateAndUpdateQnaRequest(
	List<Qna> qnas
) {

	public Map<Boolean, List<Qna100>> toMap(Long userId, List<Qna> qnas) {
		return qnas.stream()
			.map(qna -> Qna100.builder()
				.id(qna.id())
				.userId(userId)
				.question(qna.question())
				.answer(qna.answer())
				.build())
			.collect(partitioningBy(qna -> qna.getId() == null));
	}
}
