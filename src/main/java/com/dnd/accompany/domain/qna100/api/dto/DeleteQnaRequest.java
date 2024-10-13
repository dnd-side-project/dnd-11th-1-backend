package com.dnd.accompany.domain.qna100.api.dto;

import java.util.List;

public record DeleteQnaRequest(
	List<Long> ids
) {
}
