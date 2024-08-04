package com.dnd.accompany.domain.accompany.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dnd.accompany.domain.accompany.entity.AccompanyBoard;
import com.dnd.accompany.domain.accompany.entity.AccompanyTag;
import com.dnd.accompany.domain.accompany.infrastructure.AccompanyTagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccompanyTagService {
	private final AccompanyTagRepository accompanyTagRepository;

	public void save(AccompanyBoard accompanyBoard, List<String> tagNames) {
		tagNames.stream()
			.map(tagName -> AccompanyTag.builder()
				.accompanyBoard(accompanyBoard)
				.name(tagName)
				.build())
			.forEach(accompanyTagRepository::save);
	}
}	
