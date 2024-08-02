package com.dnd.accompany.domain.accompany.service;

import static com.dnd.accompany.domain.accompany.entity.AccompanyBoards.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnd.accompany.domain.accompany.api.dto.AccompanyBoardInfo;
import com.dnd.accompany.domain.accompany.api.dto.CreateAccompanyBoardRequest;
import com.dnd.accompany.domain.accompany.api.dto.CreateAccompanyBoardResponse;
import com.dnd.accompany.domain.accompany.api.dto.PageResponse;
import com.dnd.accompany.domain.accompany.entity.AccompanyBoards;
import com.dnd.accompany.domain.accompany.infrastructure.AccompanyBoardsRepository;
import com.dnd.accompany.domain.accompany.infrastructure.querydsl.AccompanyUsersRepositoryImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccompanyBoardsService {

	private final AccompanyBoardsRepository accompanyBoardsRepository;
	private final AccompanyUsersRepositoryImpl accompanyUsersRepository;

	@Transactional
	public CreateAccompanyBoardResponse create(CreateAccompanyBoardRequest request) {
		AccompanyBoards accompanyBoards = accompanyBoardsRepository.save(
			builder()
				.title(request.title())
				.content(request.content())
				.region(request.region())
				.startDate(request.startDate())
				.endDate(request.endDate())
				.headCount(1L)
				.capacity(request.capacity())
				.category(request.category())
				.preferredAge(request.preferredAge())
				.preferredGender(request.preferredGender())
				.build()
		);
		return new CreateAccompanyBoardResponse(accompanyBoards.getId());
	}

	@Transactional(readOnly = true)
	public PageResponse<AccompanyBoardInfo> readAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<AccompanyBoardInfo> pageResult = accompanyUsersRepository.findBoardsByHostUsers(pageable);

		return new PageResponse<>(pageResult.hasNext(), pageResult.getContent());
	}
}
