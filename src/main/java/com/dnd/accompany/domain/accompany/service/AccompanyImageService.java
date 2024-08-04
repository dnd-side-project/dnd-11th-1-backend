package com.dnd.accompany.domain.accompany.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dnd.accompany.domain.accompany.entity.AccompanyBoard;
import com.dnd.accompany.domain.accompany.entity.AccompanyImage;
import com.dnd.accompany.domain.accompany.infrastructure.AccompanyImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccompanyImageService {
	private final AccompanyImageRepository accompanyImageRepository;

	public void save(AccompanyBoard accompanyBoard, List<String> imageUrls) {
		imageUrls.stream()
			.map(imageUrl -> AccompanyImage.builder()
				.accompanyBoard(accompanyBoard)
				.imageUrl(imageUrl)
				.build())
			.forEach(accompanyImageRepository::save);
	}

}
