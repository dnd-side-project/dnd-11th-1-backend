package com.dnd.accompany.domain.accompany.api.dto;

import java.time.LocalDateTime;

import com.dnd.accompany.domain.accompany.api.dto.enums.Region;

public record AccompanyBoardInfo(
	Long id,
	String title,
	Region region,
	LocalDateTime startDate,
	LocalDateTime endDate,
	String nickname
) {
}