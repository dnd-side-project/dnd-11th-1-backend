package com.dnd.accompany.domain.qna100.exception;

import com.dnd.accompany.global.common.exception.BusinessException;
import com.dnd.accompany.global.common.response.ErrorCode;

public class Qna100NotFoundException extends BusinessException {
	public Qna100NotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
}
