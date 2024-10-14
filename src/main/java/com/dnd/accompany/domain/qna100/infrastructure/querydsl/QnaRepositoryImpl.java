package com.dnd.accompany.domain.qna100.infrastructure.querydsl;

import static com.dnd.accompany.domain.qna100.api.dto.FindSlicesResult.*;
import static com.dnd.accompany.domain.qna100.api.dto.PageRequest.*;
import static com.dnd.accompany.domain.qna100.entity.QQna100.*;

import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import com.dnd.accompany.domain.qna100.api.dto.QnaThumbnail;
import com.dnd.accompany.domain.qna100.infrastructure.querydsl.interfaces.QnaRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QnaRepositoryImpl implements QnaRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Slice<QnaThumbnail> findQnaThumbnails(Long cursor, int size, Long userId) {
		List<QnaThumbnail> content = queryFactory
			.select(Projections.constructor(QnaThumbnail.class,
				qna100.id,
				qna100.question,
				qna100.answer
			))
			.from(qna100)
			.where(qna100.userId.eq(userId))
			.where(cursorCondition(cursor, qna100.id))
			.orderBy(qna100.id.asc())
			.limit(size + 1)
			.fetch();

		return createSlice(size, content);
	}
}
