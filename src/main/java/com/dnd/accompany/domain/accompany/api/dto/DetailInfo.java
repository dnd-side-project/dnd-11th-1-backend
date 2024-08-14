package com.dnd.accompany.domain.accompany.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.dnd.accompany.domain.accompany.entity.enums.Category;
import com.dnd.accompany.domain.accompany.entity.enums.PreferredAge;
import com.dnd.accompany.domain.accompany.entity.enums.PreferredGender;
import com.dnd.accompany.domain.accompany.entity.enums.Region;
import com.dnd.accompany.domain.user.entity.enums.FoodPreference;
import com.dnd.accompany.domain.user.entity.enums.Gender;
import com.dnd.accompany.domain.user.entity.enums.TravelPreference;
import com.dnd.accompany.domain.user.entity.enums.TravelStyle;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailInfo {
	private Long boardId;
	private String title;
	private String content;
	private List<String> tagNames;
	private Region region;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Long headCount;
	private Long capacity;
	private Category category;
	private PreferredAge preferredAge;
	private PreferredGender preferredGender;
	private String nickname;
	private String provider;
	private int birthYear;
	private Gender gender;
	private List<TravelPreference> travelPreferences;
	private List<TravelStyle> travelStyles;
	private List<FoodPreference> foodPreferences;
	private List<String> userImageUrls;

	@Builder
	public DetailInfo(Long boardId, String title, String content, List<String> tagNames, Region region,
		LocalDateTime startDate, LocalDateTime endDate, Long headCount, Long capacity, Category category,
		PreferredAge preferredAge, PreferredGender preferredGender, String nickname, String provider, int birthYear,
		Gender gender, List<TravelPreference> travelPreferences, List<TravelStyle> travelStyles,
		List<FoodPreference> foodPreferences, List<String> userImageUrls) {
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.tagNames = tagNames;
		this.region = region;
		this.startDate = startDate;
		this.endDate = endDate;
		this.headCount = headCount;
		this.capacity = capacity;
		this.category = category;
		this.preferredAge = preferredAge;
		this.preferredGender = preferredGender;
		this.nickname = nickname;
		this.provider = provider;
		this.birthYear = birthYear;
		this.gender = gender;
		this.travelPreferences = travelPreferences;
		this.travelStyles = travelStyles;
		this.foodPreferences = foodPreferences;
		this.userImageUrls = userImageUrls;
	}
}
