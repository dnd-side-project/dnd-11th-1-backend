package com.dnd.accompany.domain.accompany.api.dto;

import com.dnd.accompany.domain.accompany.entity.enums.Role;

import jakarta.validation.constraints.NotNull;

public record ReadAccompanyRequest(
	@NotNull Long boardId,
	@NotNull Long userId,
	@NotNull Role role
) {
}
