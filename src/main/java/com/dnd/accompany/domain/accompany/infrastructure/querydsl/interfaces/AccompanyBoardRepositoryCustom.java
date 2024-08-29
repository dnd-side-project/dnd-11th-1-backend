package com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces;

import org.springframework.data.domain.Slice;

import com.dnd.accompany.domain.accompany.api.dto.FindBoardThumbnailsResult;
import com.dnd.accompany.domain.accompany.entity.enums.Region;

public interface AccompanyBoardRepositoryCustom {
	Slice<FindBoardThumbnailsResult> findBoardThumbnails(String cursor, int size, Region region);

	Slice<FindBoardThumbnailsResult> findRecordsByUserId(String cursor, int size, Long userId);

	boolean isHostOfBoard(Long userId, Long boardId);
}
