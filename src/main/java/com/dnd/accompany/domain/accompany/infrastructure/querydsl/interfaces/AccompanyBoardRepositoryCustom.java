package com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dnd.accompany.domain.accompany.api.dto.AccompanyBoardInfo;
import com.dnd.accompany.domain.accompany.api.dto.ReadAccompanyBoardResponse;

public interface AccompanyBoardRepositoryCustom {
	Page<AccompanyBoardInfo> findBoardInfos(Pageable pageable);

	Optional<ReadAccompanyBoardResponse> findDetailInfo(Long boardId);
}
