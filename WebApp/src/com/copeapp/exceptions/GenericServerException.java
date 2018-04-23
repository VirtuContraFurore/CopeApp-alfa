package com.copeapp.exceptions;

public class GenericServerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public GenericServerException (){}

	public GenericServerException (String message){
		super(message);
	}
	
	public GenericServerException (Throwable cause){
		super(cause);
	}
	
	public GenericServerException (String message, Throwable cause){
		super(message,cause);
	}
	
	public GenericServerException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
