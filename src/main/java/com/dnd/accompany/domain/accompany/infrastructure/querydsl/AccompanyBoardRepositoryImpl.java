package com.dnd.accompany.domain.accompany.infrastructure.querydsl;

import static com.dnd.accompany.domain.accompany.entity.QAccompanyBoard.*;
import static com.dnd.accompany.domain.accompany.entity.QAccompanyImage.*;
import static com.dnd.accompany.domain.accompany.entity.QAccompanyTag.*;
import static com.dnd.accompany.domain.accompany.entity.QAccompanyUser.*;
import static com.dnd.accompany.domain.accompany.entity.enums.Role.*;
import static com.dnd.accompany.domain.user.entity.QUser.*;
import static com.dnd.accompany.domain.user.entity.QUserImage.*;
import static com.dnd.accompany.domain.user.entity.QUserProfile.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.accompany.api.dto.FindBoardThumbnailsResult;
import com.dnd.accompany.domain.accompany.api.dto.FindDetailInfoResult;
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

	@Override
	public Slice<FindBoardThumbnailsResult> findBoardThumbnails(Pageable pageable, Region region) {
		List<FindBoardThumbnailsResult> content = queryFactory
			.select(Projections.constructor(FindBoardThumbnailsResult.class,
				accompanyBoard.id,
				accompanyBoard.title,
				accompanyBoard.region,
				accompanyBoard.startDate,
				accompanyBoard.endDate,
				user.nickname,
				Expressions.stringTemplate("GROUP_CONCAT(DISTINCT {0})", accompanyImage.imageUrl)))
			.from(accompanyUser)
			.join(accompanyUser.accompanyBoard, accompanyBoard)
			.join(accompanyUser.user, user)
			.leftJoin(accompanyImage).on(accompanyImage.accompanyBoard.id.eq(accompanyBoard.id))
			.where(isHost())
			.where(isRegion(region))
			.groupBy(accompanyBoard.id, accompanyBoard.title, accompanyBoard.region,
				accompanyBoard.startDate, accompanyBoard.endDate, user.nickname)
			.orderBy(accompanyBoard.updatedAt.desc(), accompanyBoard.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.fetch();

		boolean hasNext = content.size() > pageable.getPageSize();

		if (hasNext) {
			content.remove(content.size() - 1);
		}

		return new SliceImpl<>(content, pageable, hasNext);
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
				userProfile.foodPreferences,
				Expressions.stringTemplate("GROUP_CONCAT(DISTINCT {0})", accompanyTag.name),
				Expressions.stringTemplate("GROUP_CONCAT(DISTINCT {0})", userImage.imageUrl)))
			.from(accompanyUser)
			.join(accompanyUser.accompanyBoard, accompanyBoard)
			.join(accompanyUser.user, user)
			.join(userProfile).on(userProfile.userId.eq(user.id))
			.leftJoin(accompanyTag).on(accompanyTag.accompanyBoard.id.eq(accompanyBoard.id))
			.leftJoin(userImage).on(userImage.userId.eq(user.id))
			.where(accompanyBoard.id.eq(boardId))
			.where(isHost())
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
