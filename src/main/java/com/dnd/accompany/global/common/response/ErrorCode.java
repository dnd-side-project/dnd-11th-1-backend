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
	USER_NOT_FOUND(MatripConstant.NOT_FOUND, "USER-001", "존재하지 않는 회원입니다."),

	// ---- 토큰 ---- //
	INVALID_TOKEN(MatripConstant.UNAUTHORIZED, "TOKEN-001", "유효하지 않은 토큰입니다."),
	TOKEN_EXPIRED(MatripConstant.UNAUTHORIZED, "TOKEN-002", "만료된 토큰입니다."),
	REFRESH_TOKEN_NOT_FOUND(MatripConstant.NOT_FOUND, "TOKEN-003", "리프레시 토큰을 찾을 수 없습니다."),
	REFRESH_TOKEN_EXPIRED(MatripConstant.UNAUTHORIZED, "TOKEN-004", "만료된 리프레시 토큰입니다."),

	// ---- 로그인 ---- //
	INVALID_PROVIDER(MatripConstant.BAD_REQUEST, "LOGIN-001", "유효하지 않은 로그인 수단입니다."),
	INVALID_OAUTH_TOKEN(MatripConstant.BAD_REQUEST, "LOGIN-002", "유효하지 않은 OAuth 토큰입니다."),

	// ---- 프로필 ---- //
	PROFILE_ALREADY_EXISTS(MatripConstant.BAD_REQUEST, "PROFILE-001", "이미 프로필 정보가 존재합니다."),
	PROFILE_NOT_FOUND(MatripConstant.BAD_REQUEST, "PROFILE-002", "프로필 정보가 존재하지 않습니다."),

	// ---- 네트워크 ---- //
	HTTP_CLIENT_REQUEST_FAILED(MatripConstant.INTERNAL_SERVER_ERROR, "NETWORK-001", "서버 요청에 실패하였습니다."),

	// ---- 동행글 ---- //
	ACCOMPANY_BOARD_NOT_FOUND(MatripConstant.NOT_FOUND, "ACCOMPANY_BOARD-001", "동행글을 찾을 수 없습니다."),
	ACCOMPANY_BOARD_ACCESS_DENIED(MatripConstant.FORBIDDEN, "ACCOMPANY_BOARD-002", "동행글 접근 권한이 없습니다."),

	// ---- 동행 신청서 ---- //
	ACCOMPANY_REQUEST_NOT_FOUND(MatripConstant.NOT_FOUND, "ACCOMPANY_REQUEST-001", "동행 신청서를 찾을 수 없습니다."),

	// ---- 백문백답 ---- //
	QNA100_NOT_FOUND(MatripConstant.NOT_FOUND, "QNA100-001", "백문백답을 찾을 수 없습니다."),
	QNA100_ACCESS_DENIED(MatripConstant.FORBIDDEN, "QNA100-002", "백문백답 접근 권한이 없습니다."),

	// ---- 이미지 ---- //
	IMAGE_NOT_EXISTS(MatripConstant.NOT_FOUND, "IMAGE-001", "이미지를 찾을 수 없습니다."),
	FILE_IO_EXCEPTION(MatripConstant.INTERNAL_SERVER_ERROR, "IMAGE-002", "파일 생성에 실패했습니다."),

	// ---- 리뷰 ---- //
	REVIEW_NOT_FOUND(MatripConstant.NOT_FOUND, "REVIEW-001", "존재하지 않는 리뷰입니다.");

	private final Integer status;
	private final String code;
	private final String message;
}
