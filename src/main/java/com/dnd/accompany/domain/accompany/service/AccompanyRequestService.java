package com.dnd.accompany.domain.accompany.service;

import static com.dnd.accompany.domain.accompany.entity.enums.RequestState.*;
import static com.dnd.accompany.global.util.SecurityUtil.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnd.accompany.domain.accompany.api.dto.AccompanyRequestRequest;
import com.dnd.accompany.domain.accompany.entity.AccompanyBoard;
import com.dnd.accompany.domain.accompany.entity.AccompanyRequest;
import com.dnd.accompany.domain.accompany.infrastructure.AccompanyRequestRepository;
import com.dnd.accompany.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccompanyRequestService {
	private final AccompanyRequestRepository accompanyRequestRepository;

	@Transactional
	public void save(AccompanyRequestRequest request) {
		Long userId = getCurrentUserId();
		accompanyRequestRepository.save(AccompanyRequest.builder()
			.user(User.builder().id(userId).build())
			.accompanyBoard(AccompanyBoard.builder().id(request.boardId()).build())
			.requestState(HOLDING)
			.introduce(request.introduce())
			.chatLink(request.chatLink())
			.build());
	}
}
