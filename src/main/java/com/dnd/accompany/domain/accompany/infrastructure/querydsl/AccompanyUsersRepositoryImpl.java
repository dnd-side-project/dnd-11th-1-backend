package com.dnd.accompany.domain.accompany.infrastructure.querydsl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.accompany.api.dto.AccompanyBoardInfo;
import com.dnd.accompany.domain.accompany.entity.AccompanyUsers;
import com.dnd.accompany.domain.accompany.entity.QAccompanyBoards;
import com.dnd.accompany.domain.accompany.entity.QAccompanyUsers;
import com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces.AccompanyUsersRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccompanyUsersRepositoryImpl implements AccompanyUsersRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<AccompanyBoardInfo> findBoardsByHostUsers(Pageable pageable) {
		QAccompanyUsers accompanyUsers = QAccompanyUsers.accompanyUsers;
		QAccompanyBoards accompanyBoards = QAccompanyBoards.accompanyBoards;

		List<AccompanyBoardInfo> content = queryFactory.select(Projections.constructor(AccompanyBoardInfo.class,
				accompanyBoards.id,
				accompanyBoards.title,
				accompanyBoards.region,
				accompanyBoards.startDate,
				accompanyBoards.endDate,
				accompanyUsers.user.nickname))
			.from(accompanyUsers)
			.join(accompanyUsers.accompanyBoards, accompanyBoards)
			.where(accompanyUsers.role.eq(AccompanyUsers.Role.HOST))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = queryFactory.selectFrom(accompanyUsers)
			.where(accompanyUsers.role.eq(AccompanyUsers.Role.HOST))
			.fetchCount();

		return new PageImpl<>(content, pageable, total);
	}
}
