package com.dnd.accompany.domain.accompany.api.dto;

import static java.util.Collections.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.dnd.accompany.domain.accompany.entity.enums.Category;
import com.dnd.accompany.domain.accompany.entity.enums.PreferredAge;
import com.dnd.accompany.domain.accompany.entity.enums.PreferredGender;
import com.dnd.accompany.domain.accompany.entity.enums.Region;
import com.dnd.accompany.domain.user.entity.enums.Gender;

public record FindDetailInfoResult(
	Long boardId,
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
	Long userId,
	String nickname,
	String profileImageUrl,
	int birthYear,
	Gender gender,
	String tagNames,
	String imageUrls
) {
	public List<String> getTagNamesAsList() {
		if (tagNames == null || tagNames.isEmpty()) {
			return emptyList();
		}
		return Arrays.asList(tagNames.split(","));
	}

	public List<String> getImageUrlsAsList() {
		if (imageUrls == null || imageUrls.isEmpty()) {
			return emptyList();
		}
		return Arrays.asList(imageUrls.split(","));
	}
}
