package com.dnd.accompany.domain.qna100.api.dto;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringTemplate;

import io.swagger.v3.oas.annotations.media.Schema;

public record PageRequest(
	@Schema(nullable = true) Long cursor,
	@Schema(nullable = true) Integer size
) {

	public PageRequest {
		if (size == null) {
			size = 10;
		}
	}

	public static BooleanBuilder cursorCondition(Long cursor, NumberPath<Long> nextCursor) {
		BooleanBuilder builder = new BooleanBuilder();

		if (cursor == null)
			return builder;

		builder.and(nextCursor.gt(cursor));

		return builder;
	}
}
