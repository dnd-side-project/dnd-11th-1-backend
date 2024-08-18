package com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.dnd.accompany.domain.accompany.api.dto.FindBoardThumbnailsResult;
import com.dnd.accompany.domain.accompany.api.dto.UserProfileDetailInfo;
import com.dnd.accompany.domain.accompany.api.dto.UserProfileThumbnail;
import com.dnd.accompany.domain.accompany.entity.enums.Region;

public interface AccompanyBoardRepositoryCustom {
	Slice<FindBoardThumbnailsResult> findBoardThumbnails(Pageable pageable, Region region);

	Optional<UserProfileThumbnail> findUserProfileThumbnail(Long userId);

	boolean isHostOfBoard(Long userId, Long boardId);

	Optional<String> findNickname(Long userId);
}
