package com.dnd.accompany.domain.accompany.infrastructure.querydsl;

import static com.dnd.accompany.domain.accompany.entity.enums.Role.*;
import static com.dnd.accompany.domain.user.entity.QUser.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.accompany.api.dto.AccompanyBoardDetailInfo;
import com.dnd.accompany.domain.accompany.api.dto.AccompanyBoardInfo;
import com.dnd.accompany.domain.accompany.api.dto.ReadAccompanyBoardResponse;
import com.dnd.accompany.domain.accompany.api.dto.UserProfileDetailInfo;
import com.dnd.accompany.domain.accompany.entity.QAccompanyBoard;
import com.dnd.accompany.domain.accompany.entity.QAccompanyUser;
import com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces.AccompanyBoardRepositoryCustom;
import com.dnd.accompany.domain.user.entity.QUser;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccompanyBoardRepositoryImpl implements AccompanyBoardRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<AccompanyBoardInfo> findBoardInfos(Pageable pageable) {
		QAccompanyUser accompanyUser = QAccompanyUser.accompanyUser;
		QAccompanyBoard accompanyBoard = QAccompanyBoard.accompanyBoard;

		List<AccompanyBoardInfo> content = queryFactory.select(Projections.constructor(AccompanyBoardInfo.class,
				accompanyBoard.id,
				accompanyBoard.title,
				accompanyBoard.region,
				accompanyBoard.startDate,
				accompanyBoard.endDate,
				user.nickname))
			.from(accompanyUser)
			.join(accompanyUser.accompanyBoard, accompanyBoard)
			.join(accompanyUser.user, user) // 추가된 부분
			.where(accompanyUser.role.eq(HOST))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		long total = queryFactory.select(accompanyUser.count())
			.from(accompanyUser)
			.join(accompanyUser.accompanyBoard, accompanyBoard)
			.join(accompanyUser.user, user)
			.where(accompanyUser.role.eq(HOST))
			.fetchOne();

		return new PageImpl<>(content, pageable, total);
	}

	/**
	 * 동행글, 프로필 정보를 한번에 가져옵니다.
	 */

	@Override
	public Optional<ReadAccompanyBoardResponse> findDetailInfo(Long boardId) {
		QAccompanyBoard accompanyBoard = QAccompanyBoard.accompanyBoard;
		QAccompanyUser accompanyUser = QAccompanyUser.accompanyUser;
		QUser user = QUser.user;

		Tuple result = queryFactory
			.select(Projections.constructor(AccompanyBoardDetailInfo.class,
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
					accompanyBoard.preferredGender),
				Projections.constructor(UserProfileDetailInfo.class,
					user.nickname))
			.from(accompanyBoard)
			.leftJoin(accompanyUser).on(accompanyUser.accompanyBoard.id.eq(accompanyBoard.id)
				.and(accompanyUser.role.eq(HOST)))
			.leftJoin(user).on(user.id.eq(accompanyUser.user.id))
			.where(accompanyBoard.id.eq(boardId))
			.fetchOne();

		if (result == null) {
			return Optional.empty();
		}

		AccompanyBoardDetailInfo detailInfo = result.get(0, AccompanyBoardDetailInfo.class);
		UserProfileDetailInfo profileInfo = result.get(1, UserProfileDetailInfo.class);

		ReadAccompanyBoardResponse response = new ReadAccompanyBoardResponse(detailInfo, profileInfo);

		return Optional.of(response);
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
