package com.dnd.accompany.domain.qna100.api.dto;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class FindSlicesResult {
	private final Long cursor;

	public static <T extends FindSlicesResult> Long getLastCursor(List<T> result) {
		if (result.isEmpty())
			return null;

		return result.get(result.size() - 1).getCursor();
	}

	public static <T extends FindSlicesResult> Slice<T> createSlice(int size, List<T> content) {
		boolean hasNext = content.size() > size;

		if (hasNext) {
			content.remove(content.size() - 1);
		}

		return new SliceImpl<>(content, PageRequest.of(0, size), hasNext);
	}
}
