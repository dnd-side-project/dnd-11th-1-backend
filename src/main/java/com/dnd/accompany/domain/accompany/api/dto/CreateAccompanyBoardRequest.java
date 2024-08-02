package com.dnd.accompany.domain.accompany.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.dnd.accompany.domain.accompany.api.dto.enums.Category;
import com.dnd.accompany.domain.accompany.api.dto.enums.PreferredAge;
import com.dnd.accompany.domain.accompany.api.dto.enums.PreferredGender;
import com.dnd.accompany.domain.accompany.api.dto.enums.Region;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAccompanyBoardRequest(
	@NotNull String title,
	@NotNull @Max(2000) String content,
	@NotNull Region region,
	@NotNull LocalDateTime startDate,
	@NotNull LocalDateTime endDate,
	@NotNull Long capacity,
	@NotNull Category category,
	@NotNull PreferredAge preferredAge,
	@NotNull PreferredGender preferredGender,
	List<@NotNull @Size(max = 2000) String> imageUrls,
	List<@NotNull @Size(max = 255) String> tagNames
) {
}
