package com.copeapp.exception;

public class SurveyRequestFailedExcption extends CopeAppGenericException {

	private static final long serialVersionUID = 1L;

	public SurveyRequestFailedExcption(int httpStatus, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(httpStatus, message, cause, enableSuppression, writableStackTrace);
	}

	public SurveyRequestFailedExcption(int httpStatus, String message, Throwable cause) {
		super(httpStatus, message, cause);
	}

	public SurveyRequestFailedExcption(int httpStatus, String message) {
		super(httpStatus, message);
	}

	public SurveyRequestFailedExcption(int httpStatus, Throwable cause) {
		super(httpStatus, cause);
	}

	public SurveyRequestFailedExcption(int httpStatus) {
		super(httpStatus);
	}
	
}
