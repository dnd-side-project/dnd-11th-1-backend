package com.dnd.accompany.domain.accompany.api.dto;

import java.util.List;

import com.dnd.accompany.domain.user.entity.enums.TravelStyle;

public record ReadAccompanyResponse(
	AccompanyBoardThumbnail boardThumbnail,
	UserProfileDetailInfo profileInfo,
	AccompanyRequestDetailInfo requestInfo
) {
}
