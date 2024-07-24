package com.dnd.accompany.global.common.exception;

import com.dnd.accompany.global.common.response.ErrorCode;

public class NotFoundException extends BusinessException {
	public NotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
}
