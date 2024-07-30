package com.dnd.accompany.domain.auth.dto;

import org.springframework.util.Assert;

import lombok.Getter;

@Getter
public class AuthUserInfo {
	private final Long userId;

	public AuthUserInfo(Long userId) {
		Assert.notNull(userId, "userId는 필수 값입니다.");
		this.userId = userId;
	}
}
