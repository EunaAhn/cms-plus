package kr.or.kosa.cmsplusmain.domain.base.error.exception;

import kr.or.kosa.cmsplusmain.domain.base.error.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private final ErrorCode errorCode;

	public BusinessException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
