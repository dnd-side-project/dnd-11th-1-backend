package com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces;

import java.util.Optional;

import com.dnd.accompany.domain.accompany.entity.AccompanyRequest;
import com.dnd.accompany.domain.accompany.entity.enums.Role;

public interface AccompanyRequestRepositoryCustom {
	Optional<AccompanyRequest> findRequestDetailInfo(Long boardId, Long userId, Role role);
}
