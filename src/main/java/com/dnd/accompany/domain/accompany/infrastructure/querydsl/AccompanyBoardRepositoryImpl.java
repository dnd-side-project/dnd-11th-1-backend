package com.dnd.accompany.domain.accompany.infrastructure.querydsl;

import static com.dnd.accompany.domain.accompany.entity.QAccompanyBoard.*;
import static com.dnd.accompany.domain.accompany.entity.QAccompanyImage.*;
import static com.dnd.accompany.domain.accompany.entity.QAccompanyUser.*;
import static com.dnd.accompany.domain.accompany.entity.enums.Role.*;
import static com.dnd.accompany.domain.user.entity.QUser.*;
import static com.dnd.accompany.domain.user.entity.QUserProfile.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.accompany.api.dto.FindBoardThumbnailsResult;
import com.dnd.accompany.domain.accompany.api.dto.FindDetailInfoResult;
import com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces.AccompanyBoardRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccompanyBoardRepositoryImpl implements AccompanyBoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public static BooleanExpression isHost() {
		return accompanyUser.role.eq(HOST);
	}

	@Override
	public List<FindBoardThumbnailsResult> findBoardThumbnails(Pageable pageable, int limit) {
		return queryFactory.select(Projections.constructor(FindBoardThumbnailsResult.class,
				accompanyBoard.id,
				accompanyBoard.title,
				accompanyBoard.region,
				accompanyBoard.startDate,
				accompanyBoard.endDate,
				user.nickname,
				accompanyImage.imageUrl))
			.from(accompanyUser)
			.join(accompanyUser.accompanyBoard, accompanyBoard)
			.join(accompanyUser.user, user)
			.leftJoin(accompanyImage).on(accompanyImage.accompanyBoard.id.eq(accompanyBoard.id)) // LEFT JOIN
			.where(accompanyUser.role.eq(HOST))
			.offset(pageable.getOffset())
			.limit(limit)
			.fetch();
	}

	/**
	 * 동행글, 프로필 정보를 한번에 가져옵니다.
	 */
	@Override
	public Optional<FindDetailInfoResult> findDetailInfo(Long boardId) {
		FindDetailInfoResult result = queryFactory
			.select(Projections.constructor(FindDetailInfoResult.class,
				accompanyBoard.id,
				accompanyBoard.title,
				accompanyBoard.content,
				accompanyBoard.region,
				accompanyBoard.startDate,
				accompanyBoard.endDate,
				accompanyBoard.headCount,
				accompanyBoard.capacity,
				accompanyBoard.category,
				accompanyBoard.preferredAge,
				accompanyBoard.preferredGender,
				user.nickname,
				user.provider,
				userProfile.birthYear,
				userProfile.gender,
				userProfile.travelPreferences,
				userProfile.travelStyles,
				userProfile.foodPreferences))
			.from(accompanyBoard)
			.join(accompanyUser).on(accompanyUser.accompanyBoard.id.eq(accompanyBoard.id)
				.and(isHost()))
			.join(user).on(user.id.eq(accompanyUser.user.id))
			.join(userProfile).on(userProfile.userId.eq(user.id))
			.where(accompanyBoard.id.eq(boardId))
			.fetchOne();

		return Optional.ofNullable(result);
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
}
