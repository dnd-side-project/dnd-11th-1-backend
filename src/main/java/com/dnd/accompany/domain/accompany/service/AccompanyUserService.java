package com.dnd.accompany.domain.accompany.service;

import org.springframework.stereotype.Service;

import com.dnd.accompany.domain.accompany.entity.AccompanyBoard;
import com.dnd.accompany.domain.accompany.entity.AccompanyUser;
import com.dnd.accompany.domain.accompany.entity.enums.Role;
import com.dnd.accompany.domain.accompany.infrastructure.AccompanyBoardRepository;
import com.dnd.accompany.domain.accompany.infrastructure.AccompanyUserRepository;
import com.dnd.accompany.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccompanyUserService {
	private final AccompanyUserRepository accompanyUserRepository;
	private final AccompanyBoardRepository accompanyBoardRepository;

	public void save(Long userId, AccompanyBoard accompanyBoard, Role role) {
		accompanyUserRepository.save(AccompanyUser.builder()
			.accompanyBoard(accompanyBoard)
			.user(User.builder().id(userId).build())
			.role(role)
			.build());
	}

	public boolean isHostOfBoard(Long userId, Long boardId) {
		return accompanyBoardRepository.isHostOfBoard(userId, boardId);
	}
}
