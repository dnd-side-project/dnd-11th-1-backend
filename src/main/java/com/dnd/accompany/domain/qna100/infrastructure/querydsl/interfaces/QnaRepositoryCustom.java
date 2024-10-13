package com.dnd.accompany.domain.qna100.infrastructure.querydsl.interfaces;

import org.springframework.data.domain.Slice;

import com.dnd.accompany.domain.qna100.api.dto.QnaThumbnail;

public interface QnaRepositoryCustom {
	Slice<QnaThumbnail> findQnaThumbnails(Long cursor, int size, Long userId);
}
