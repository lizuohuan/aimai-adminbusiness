package com.magic.aimai.business.exception;

import java.io.Serializable;

/**
 * 通用接口异常类.
 * @author hep
 *
 */
public class InterfaceCommonException extends RuntimeException implements Serializable {
	/**
	 * 异常编码.
	 */
	private int errorCode;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public InterfaceCommonException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
}

