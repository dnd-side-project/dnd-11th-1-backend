package com.dnd.accompany.domain.accompany.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dnd.accompany.domain.accompany.entity.AccompanyRequest;
import com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces.AccompanyRequestRepositoryCustom;

public interface AccompanyRequestRepository extends JpaRepository<AccompanyRequest, Long>, AccompanyRequestRepositoryCustom {
	void deleteByAccompanyBoardId(Long boardId);
}
