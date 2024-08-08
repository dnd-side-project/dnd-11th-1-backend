package com.dnd.accompany.domain.accompany.exception;

import com.dnd.accompany.global.common.response.ErrorCode;

public class AccompanyBoardOffsetExceededException extends AccompanyBoardException {
	public AccompanyBoardOffsetExceededException(ErrorCode errorCode) {
		super(errorCode);
	}
}
