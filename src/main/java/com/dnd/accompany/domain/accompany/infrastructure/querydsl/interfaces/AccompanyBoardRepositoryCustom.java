package com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.dnd.accompany.domain.accompany.api.dto.FindBoardThumbnailsResult;
import com.dnd.accompany.domain.accompany.api.dto.FindDetailInfoResult;

public interface AccompanyBoardRepositoryCustom {
	List<FindBoardThumbnailsResult> findBoardThumbnails(Pageable pageable, int limit);

	Optional<FindDetailInfoResult> findDetailInfo(Long boardId);

	boolean isHostOfBoard(Long userId, Long boardId);
}
