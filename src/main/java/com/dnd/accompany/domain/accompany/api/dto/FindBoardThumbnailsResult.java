package com.dnd.accompany.domain.accompany.api.dto;

import java.time.LocalDateTime;

import com.dnd.accompany.domain.accompany.entity.enums.Region;

public record FindBoardThumbnailsResult(
	Long boardId,
	String title,
	Region region,
	LocalDateTime startDate,
	LocalDateTime endDate,
	String nickname,
	String imageUrl
) {
}
