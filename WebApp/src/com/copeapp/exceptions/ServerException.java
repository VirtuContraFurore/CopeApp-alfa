package com.copeapp.exceptions;

import lombok.Getter;

@Getter
public class ServerException extends Exception {

	private static final long serialVersionUID = 1L;
	private ExType exceptionType = null;
	
	public ServerException (ExType exceptionType){
		super();
		this.exceptionType = exceptionType;
	}

	public ServerException (String message, ExType exceptionType){
		super(message);
		this.exceptionType = exceptionType;
	}
	
	public ServerException (Throwable cause, ExType exceptionType){
		super(cause);
		this.exceptionType = exceptionType;
	}
	
	public ServerException (String message, Throwable cause, ExType exceptionType){
		super(message,cause);
		this.exceptionType = exceptionType;
	}
	
	public ServerException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ExType exceptionType){
		super(message, cause, enableSuppression, writableStackTrace);
		this.exceptionType = exceptionType;
	}
	
	public String getDescription () {
		return getExceptionDescription(exceptionType);
	}

	public static String getExceptionDescription (ExType exceptionType) {
		switch (exceptionType) {
			case NOT_NULL_ANNOTATION_NOT_RESPECTED: return "In una classe, l'attributo marcato con @NotNull risulta essere nullo";
			case JSON_FORMAT_ERROR: return "ObjectMapper ha fallitu durante una conversione: gli attributi previsti non coincidono con quelli contenuti nel json";
			case GENERIC_SERVER_ERROR: return "Errore generico interno al server";
			case WRONG_USER_ID: return "E' stato cercato un utente con un certo id che però non è presente del DB";
			default: return "Tipo di eccezione non individuato";
		}
	}
}
