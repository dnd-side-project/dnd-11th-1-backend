package com.dnd.accompany.domain.accompany.infrastructure.querydsl;

import static com.dnd.accompany.domain.accompany.entity.QAccompanyBoard.*;
import static com.dnd.accompany.domain.accompany.entity.QAccompanyRequest.*;
import static com.dnd.accompany.domain.accompany.entity.QAccompanyUser.*;
import static com.dnd.accompany.domain.accompany.entity.enums.RequestState.*;
import static com.dnd.accompany.domain.user.entity.QUser.*;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.accompany.entity.AccompanyRequest;
import com.dnd.accompany.domain.accompany.entity.enums.RequestState;
import com.dnd.accompany.domain.accompany.entity.enums.Role;
import com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces.AccompanyRequestRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccompanyRequestRepositoryImpl implements AccompanyRequestRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<AccompanyRequest> findRequestDetailInfo(Long boardId, Long userId, Role role) {
		return Optional.of(queryFactory
			.selectFrom(accompanyRequest)
			.join(accompanyRequest.accompanyBoard, accompanyBoard)
			.join(accompanyUser).on(accompanyUser.accompanyBoard.id.eq(accompanyBoard.id))
			.where(accompanyBoard.id.eq(boardId))
			.where(accompanyRequest.user.id.eq(userId))
			.where(accompanyRequest.requestState.eq(HOLDING))
			.where(accompanyUser.role.eq(role))
			.fetchOne());
	}
}
