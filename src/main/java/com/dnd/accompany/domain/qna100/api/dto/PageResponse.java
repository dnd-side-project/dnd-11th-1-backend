package com.dnd.accompany.domain.qna100.api.dto;

import java.util.List;

public record PageResponse<T>(
	boolean hasNext,
	List<T> data,
	Long cursor
) {
}