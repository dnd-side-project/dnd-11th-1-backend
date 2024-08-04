package com.dnd.accompany.global.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.dnd.accompany.domain.auth.dto.jwt.JwtAuthentication;
import com.dnd.accompany.domain.auth.dto.jwt.JwtAuthenticationToken;

public class SecurityUtil {
	private SecurityUtil() {
	}

	public static Long getCurrentUserId() {
		JwtAuthenticationToken authentication = (JwtAuthenticationToken)SecurityContextHolder.getContext()
			.getAuthentication();
		JwtAuthentication principal = (JwtAuthentication)authentication.getPrincipal();
		return principal.getId();
	}
}
