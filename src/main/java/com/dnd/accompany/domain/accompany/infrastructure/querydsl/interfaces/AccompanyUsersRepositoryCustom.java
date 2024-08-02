package com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dnd.accompany.domain.accompany.api.dto.AccompanyBoardInfo;

public interface AccompanyUsersRepositoryCustom {
	Page<AccompanyBoardInfo> findBoardsByHostUsers(Pageable pageable);
}
