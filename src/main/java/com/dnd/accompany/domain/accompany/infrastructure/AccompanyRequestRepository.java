package com.dnd.accompany.domain.accompany.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.accompany.entity.AccompanyRequest;
import com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces.AccompanyBoardRepositoryCustom;

@Repository
public interface AccompanyRequestRepository
	extends JpaRepository<AccompanyRequest, Long>, AccompanyBoardRepositoryCustom {
}
