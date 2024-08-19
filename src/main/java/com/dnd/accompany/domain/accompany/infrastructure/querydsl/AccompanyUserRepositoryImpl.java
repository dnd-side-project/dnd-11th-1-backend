package com.dnd.accompany.domain.accompany.infrastructure.querydsl;

import static com.dnd.accompany.domain.user.entity.QUser.*;
import static com.dnd.accompany.domain.user.entity.QUserProfile.*;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.accompany.api.dto.UserProfileThumbnail;
import com.dnd.accompany.domain.accompany.infrastructure.querydsl.interfaces.AccompanyUserRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccompanyUserRepositoryImpl implements AccompanyUserRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public Optional<UserProfileThumbnail> findUserProfileThumbnail(Long userId) {
		return Optional.of(queryFactory
			.select(Projections.constructor(UserProfileThumbnail.class,
				user.id,
				user.nickname,
				user.profileImageUrl,
				userProfile.birthYear,
				userProfile.gender
			))
			.from(user)
			.leftJoin(userProfile).on(userProfile.userId.eq(user.id))
			.where(user.id.eq(userId))
			.groupBy(
				user.id,
				user.nickname,
				user.profileImageUrl,
				userProfile.birthYear,
				userProfile.gender
			)
			.fetchOne());
	}
}
