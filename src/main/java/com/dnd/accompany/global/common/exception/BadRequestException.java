package com.dnd.accompany.global.common.exception;

import com.dnd.accompany.global.common.response.ErrorCode;

public class BadRequestException extends BusinessException {
	public BadRequestException(final ErrorCode errorCode) {
		super(errorCode);
	}
}
