package com.dnd.accompany.domain.accompany.api.dto;

import static java.util.Collections.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.dnd.accompany.domain.accompany.entity.enums.Category;
import com.dnd.accompany.domain.accompany.entity.enums.PreferredAge;
import com.dnd.accompany.domain.accompany.entity.enums.PreferredGender;
import com.dnd.accompany.domain.accompany.entity.enums.Region;
import com.dnd.accompany.domain.user.entity.enums.FoodPreference;
import com.dnd.accompany.domain.user.entity.enums.Gender;
import com.dnd.accompany.domain.user.entity.enums.TravelPreference;
import com.dnd.accompany.domain.user.entity.enums.TravelStyle;

public record FindDetailInfoResult(
	Long boardId,
	String title,
	String content,
	String tagNames,
	Region region,
	LocalDateTime startDate,
	LocalDateTime endDate,
	Long headCount,
	Long capacity,
	Category category,
	PreferredAge preferredAge,
	PreferredGender preferredGender,
	String nickname,
	String provider,
	int birthYear,
	Gender gender,
	List<TravelPreference> travelPreferences,
	List<TravelStyle> travelStyles,
	List<FoodPreference> foodPreferences,
	String userImageUrls
) {
	public List<String> getTagNamesAsList() {
		if (tagNames == null || tagNames.isEmpty()) {
			return emptyList();
		}
		return Arrays.asList(tagNames.split(","));
	}

	public List<String> getUserImageUrlsAsList() {
		if (userImageUrls == null || userImageUrls.isEmpty()) {
			return emptyList();
		}
		return Arrays.asList(userImageUrls.split(","));
	}
}
