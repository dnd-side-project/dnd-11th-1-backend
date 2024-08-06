package com.dnd.accompany.domain.accompany.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.dnd.accompany.domain.accompany.entity.enums.Category;
import com.dnd.accompany.domain.accompany.entity.enums.PreferredAge;
import com.dnd.accompany.domain.accompany.entity.enums.PreferredGender;
import com.dnd.accompany.domain.accompany.entity.enums.Region;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAccompanyBoardRequest(
	@NotNull String title,
	@NotNull @Size(max = 2000) String content,
	@NotNull Region region,
	@NotNull LocalDateTime startDate,
	@NotNull LocalDateTime endDate,
	@NotNull Long capacity,
	@NotNull Category category,
	@NotNull PreferredAge preferredAge,
	@NotNull PreferredGender preferredGender,
	List<@NotNull @Size(max = 10000) String> imageUrls,
	List<@NotNull @Size(max = 1000) String> tagNames
) {
}