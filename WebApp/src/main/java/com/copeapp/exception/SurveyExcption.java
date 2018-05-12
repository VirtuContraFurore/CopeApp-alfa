package com.copeapp.exception;

public class SurveyExcption extends CopeAppGenericException {

	private static final long serialVersionUID = 1L;

	public SurveyExcption(int httpStatus, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(httpStatus, message, cause, enableSuppression, writableStackTrace);
	}

	public SurveyExcption(int httpStatus, String message, Throwable cause) {
		super(httpStatus, message, cause);
	}

	public SurveyExcption(int httpStatus, String message) {
		super(httpStatus, message);
	}

	public SurveyExcption(int httpStatus, Throwable cause) {
		super(httpStatus, cause);
	}

	public SurveyExcption(int httpStatus) {
		super(httpStatus);
	}
	
}
