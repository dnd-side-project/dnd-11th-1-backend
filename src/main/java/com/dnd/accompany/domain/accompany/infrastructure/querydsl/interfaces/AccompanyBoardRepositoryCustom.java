package com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces;

import java.util.List;
import java.util.Optional;

import com.dnd.accompany.domain.accompany.api.dto.FindBoardThumbnailsResult;
import com.dnd.accompany.domain.accompany.api.dto.FindDetailInfoResult;

public interface AccompanyBoardRepositoryCustom {
	List<FindBoardThumbnailsResult> findBoardThumbnails();

	Optional<FindDetailInfoResult> findDetailInfo(Long boardId);

	boolean isHostOfBoard(Long userId, Long boardId);
}
