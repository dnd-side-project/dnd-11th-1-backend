package com.dnd.accompany.domain.accompany.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public record AccompanyRequestRequest(
	@NotNull Long boardId,
	@NotNull @Max(1500) String introduce,
	@NotNull String chatLink
) {
}
