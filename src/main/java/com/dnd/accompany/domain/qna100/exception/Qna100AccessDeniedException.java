package com.dnd.accompany.domain.qna100.exception;

import com.dnd.accompany.global.common.exception.BusinessException;
import com.dnd.accompany.global.common.response.ErrorCode;

public class Qna100AccessDeniedException extends BusinessException {
	public Qna100AccessDeniedException(ErrorCode errorCode) {
		super(errorCode);
	}
}
