package com.dnd.accompany.domain.accompany.api.dto;

import java.time.LocalDateTime;

import com.dnd.accompany.domain.accompany.api.dto.enums.Category;
import com.dnd.accompany.domain.accompany.api.dto.enums.PreferredAge;
import com.dnd.accompany.domain.accompany.api.dto.enums.PreferredGender;
import com.dnd.accompany.domain.accompany.api.dto.enums.Region;

public record ReadAccompanyBoardResponse(
	Long id,
	String title,
	String content,
	Region region,
	LocalDateTime startDate,
	LocalDateTime endDate,
	Long headCount,
	Long capacity,
	Category category,
	PreferredAge preferredAge,
	PreferredGender preferredGender,
	String nickname
	// profile 추가예정
) {
}
