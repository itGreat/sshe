package com.gc.common;

/**
 * @author gongchang
 * 描述：自定义验证异常
 * 时间：2014年9月23日 下午3:31:21
 */
@SuppressWarnings("serial")
public class ValidateException extends RuntimeException{

	public ValidateException() {
		super();
	}

	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidateException(String message) {
		super(message);
	}

	public ValidateException(Throwable cause) {
		super(cause);
	}
}
