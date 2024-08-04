package com.dnd.accompany.domain.accompany.service;

import static com.dnd.accompany.domain.accompany.entity.enums.Role.*;
import static com.dnd.accompany.global.util.SecurityUtil.*;

import org.springframework.stereotype.Service;

import com.dnd.accompany.domain.accompany.entity.AccompanyBoard;
import com.dnd.accompany.domain.accompany.entity.AccompanyUser;
import com.dnd.accompany.domain.accompany.entity.enums.Role;
import com.dnd.accompany.domain.accompany.infrastructure.AccompanyUserRepository;
import com.dnd.accompany.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccompanyUserService {
	private final AccompanyUserRepository accompanyUserRepository;

	public void save(AccompanyBoard accompanyBoard, Role role) {
		Long userId = getCurrentUserId();
		accompanyUserRepository.save(AccompanyUser.builder()
			.accompanyBoard(accompanyBoard)
			.user(User.builder().id(userId).build())
			.role(role)
			.build());
	}

	public boolean isExist(Long userId, Long boardId) {
		return accompanyUserRepository.existsByUserIdAndAccompanyBoardIdAndRole(userId, boardId, HOST);
	}
}
