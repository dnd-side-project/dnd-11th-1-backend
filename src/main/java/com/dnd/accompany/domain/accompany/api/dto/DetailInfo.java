package com.dnd.accompany.domain.accompany.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.dnd.accompany.domain.accompany.entity.enums.Category;
import com.dnd.accompany.domain.accompany.entity.enums.PreferredAge;
import com.dnd.accompany.domain.accompany.entity.enums.PreferredGender;
import com.dnd.accompany.domain.accompany.entity.enums.Region;
import com.dnd.accompany.domain.user.entity.enums.Gender;

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
	private List<String> imageUrls;
	private Region region;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Long headCount;
	private Long capacity;
	private List<Category> categories;
	private PreferredAge preferredAge;
	private PreferredGender preferredGender;
	private Long userId;
	private String nickname;
	private String profileImageUrl;
	private int birthYear;
	private Gender gender;

	@Builder
	public DetailInfo(Long boardId, String title, String content, List<String> tagNames, List<String> imageUrls,
		Region region, LocalDateTime startDate, LocalDateTime endDate, Long headCount, Long capacity,
		List<Category> categories, PreferredAge preferredAge, PreferredGender preferredGender, Long userId,
		String nickname, String profileImageUrl, int birthYear, Gender gender) {
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.tagNames = tagNames;
		this.imageUrls = imageUrls;
		this.region = region;
		this.startDate = startDate;
		this.endDate = endDate;
		this.headCount = headCount;
		this.capacity = capacity;
		this.categories = categories;
		this.preferredAge = preferredAge;
		this.preferredGender = preferredGender;
		this.userId = userId;
		this.nickname = nickname;
		this.profileImageUrl = profileImageUrl;
		this.birthYear = birthYear;
		this.gender = gender;
	}
}
