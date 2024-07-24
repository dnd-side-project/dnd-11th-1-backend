package com.dnd.accompany.global.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	BAD_REQUEST(MatripConstant.BAD_REQUEST, "GLOBAL-400", "잘못된 요청입니다."),
	ACCESS_DENIED(MatripConstant.FORBIDDEN, "GLOBAL-403", "접근 권한이 없습니다."),
	INTERNAL_SERVER(MatripConstant.INTERNAL_SERVER_ERROR, "GLOBAL-500", "서버 내부 오류입니다."),

	// ---- 유저 ---- //
	USER_NOT_FOUND(MatripConstant.NOT_FOUND, "USER-001", "존재하지 않는 회원입니다.");

	private final Integer status;
	private final String code;
	private final String message;
}
