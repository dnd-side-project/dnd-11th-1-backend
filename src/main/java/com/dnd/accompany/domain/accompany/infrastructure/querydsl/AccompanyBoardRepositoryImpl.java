package com.dnd.accompany.domain.accompany.infrastructure.querydsl;

import static com.dnd.accompany.domain.accompany.api.dto.FindSlicesResult.*;
import static com.dnd.accompany.domain.accompany.api.dto.PageRequest.*;
import static com.dnd.accompany.domain.accompany.entity.QAccompanyBoard.*;
import static com.dnd.accompany.domain.accompany.entity.QAccompanyImage.*;
import static com.dnd.accompany.domain.accompany.entity.QAccompanyRequest.*;
import static com.dnd.accompany.domain.accompany.entity.QAccompanyUser.*;
import static com.dnd.accompany.domain.accompany.entity.enums.Role.*;
import static com.dnd.accompany.domain.user.entity.QUser.*;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.accompany.api.dto.FindBoardThumbnailsResult;
import com.dnd.accompany.domain.accompany.api.dto.FindSlicesResult;
import com.dnd.accompany.domain.accompany.entity.enums.Region;
import com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces.AccompanyBoardRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccompanyBoardRepositoryImpl implements AccompanyBoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Slice<FindBoardThumbnailsResult> findBoardThumbnails(String cursor, int size, Region region) {
		List<FindBoardThumbnailsResult> content = queryFactory
			.select(Projections.constructor(FindBoardThumbnailsResult.class,
				accompanyBoard.id,
				accompanyBoard.title,
				accompanyBoard.region,
				accompanyBoard.startDate,
				accompanyBoard.endDate,
				user.nickname,
				Expressions.stringTemplate("GROUP_CONCAT(DISTINCT {0})", accompanyImage.imageUrl),
				Expressions.stringTemplate(
					"CONCAT(DATE_FORMAT({0}, '%Y%m%d%H%i%S'), LPAD(CAST({1} AS STRING), 6, '0'))",
					accompanyBoard.updatedAt,
					accompanyBoard.id
				))
			)
			.from(accompanyUser)
			.join(accompanyUser.accompanyBoard, accompanyBoard)
			.join(accompanyUser.user, user)
			.leftJoin(accompanyImage).on(accompanyImage.accompanyBoard.id.eq(accompanyBoard.id))
			.where(isHost())
			.where(isRegion(region))
			.where(cursorCondition(cursor, accompanyBoard.updatedAt, accompanyBoard.id))
			.groupBy(accompanyBoard.id, accompanyBoard.title, accompanyBoard.region,
				accompanyBoard.startDate, accompanyBoard.endDate, user.nickname)
			.orderBy(accompanyBoard.updatedAt.desc(), accompanyBoard.id.desc())
			.limit(size + 1)
			.fetch();

		return createSlice(size, content);
	}

	@Override
	public Slice<FindBoardThumbnailsResult> findRecordsByUserId(String cursor, int size, Long userId) {
		List<FindBoardThumbnailsResult> content = queryFactory
			.select(Projections.constructor(FindBoardThumbnailsResult.class,
				accompanyBoard.id,
				accompanyBoard.title,
				accompanyBoard.region,
				accompanyBoard.startDate,
				accompanyBoard.endDate,
				user.nickname,
				Expressions.stringTemplate("GROUP_CONCAT(DISTINCT {0})", accompanyImage.imageUrl),
				Expressions.stringTemplate(
					"CONCAT(DATE_FORMAT({0}, '%Y%m%d%H%i%S'), LPAD(CAST({1} AS STRING), 6, '0'))",
					accompanyUser.updatedAt,
					accompanyUser.id
				))
			)
			.from(accompanyUser)
			.join(accompanyUser.accompanyBoard, accompanyBoard)
			.join(accompanyUser.user, user)
			.leftJoin(accompanyImage).on(accompanyImage.accompanyBoard.id.eq(accompanyBoard.id))
			.where(accompanyUser.user.id.eq(userId))
			.where(cursorCondition(cursor, accompanyUser.updatedAt, accompanyUser.id))
			.groupBy(accompanyBoard.id, accompanyBoard.title, accompanyBoard.region,
				accompanyBoard.startDate, accompanyBoard.endDate, user.nickname,
				accompanyUser.id)
			.orderBy(accompanyUser.updatedAt.desc(), accompanyUser.id.desc())
			.limit(size + 1)
			.fetch();

		return createSlice(size, content);
	}

	@Override
	public boolean isHostOfBoard(Long userId, Long boardId) {
		Integer fetchCount = queryFactory.selectOne()
			.from(accompanyUser)
			.where(
				accompanyUser.user.id.eq(userId)
					.and(accompanyUser.accompanyBoard.id.eq(boardId))
					.and(isHost())
			)
			.fetchFirst();

		return fetchCount != null;
	}

	private BooleanExpression isHost() {
		return accompanyUser.role.eq(HOST);
	}

	private BooleanBuilder isRegion(Region region) {
		BooleanBuilder clause = new BooleanBuilder();
		if (region != null) {
			clause.and(accompanyBoard.region.eq(region));
		}

		return clause;
	}
}
