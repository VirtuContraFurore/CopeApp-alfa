package com.copeapp.exception;

public class LoginFailedException extends CopeAppGenericException{
	
	private static final long serialVersionUID = 1L;

	public LoginFailedException(int httpStatus, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(httpStatus, message, cause, enableSuppression, writableStackTrace);
	}

	public LoginFailedException(int httpStatus, String message, Throwable cause) {
		super(httpStatus, message, cause);
	}

	public LoginFailedException(int httpStatus, String message) {
		super(httpStatus, message);
	}

	public LoginFailedException(int httpStatus, Throwable cause) {
		super(httpStatus, cause);
	}

	public LoginFailedException(int httpStatus) {
		super(httpStatus);
	}
	
	

}
