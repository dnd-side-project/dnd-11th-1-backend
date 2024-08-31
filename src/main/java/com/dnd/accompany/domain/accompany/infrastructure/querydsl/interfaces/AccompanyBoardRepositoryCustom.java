package com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.dnd.accompany.domain.accompany.api.dto.FindBoardThumbnailsResult;
import com.dnd.accompany.domain.accompany.api.dto.FindRecordThumbnailsResult;
import com.dnd.accompany.domain.accompany.entity.enums.Region;

public interface AccompanyBoardRepositoryCustom {

  Slice<FindBoardThumbnailsResult> findBoardThumbnails(String cursor, int size, Region region);

	Slice<FindRecordThumbnailsResult> findRecordThumbnails(String cursor, int size, Long userId);
  
	Slice<FindBoardThumbnailsResult> findBoardThumbnailsByKeyword(String cursor, int size, String keyword);

	boolean isHostOfBoard(Long userId, Long boardId);
}
