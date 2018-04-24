package com.copeapp.dto.commons;

import com.copeapp.exceptions.ServerException;

import lombok.Data;

@Data
public class GenericExceptionDTO {

	private StackTraceElement[] stackTrace = null;
	private Integer httpStatus = null;
	private String descriptionForUser = null;
	private String descriptionForDebugger = null;
	
	public GenericExceptionDTO(ServerException genericServerException, Integer httpStatus, String descriptionForUser) {
		
		stackTrace = (genericServerException.getCause() != null)? (genericServerException.getCause().getStackTrace()):(null);
		this.httpStatus = httpStatus;
		this.descriptionForUser = descriptionForUser;
		descriptionForDebugger = genericServerException.getDescription();
	}
	
	public GenericExceptionDTO(StackTraceElement[] stackTrace, int httpStatus, String descriptionForUser, String descriptionForDebugger) {
		this.stackTrace = stackTrace;
		this.httpStatus = httpStatus;
		this.descriptionForUser = descriptionForUser;
		this.descriptionForDebugger = descriptionForDebugger;
	}
	
	public GenericExceptionDTO(StackTraceElement[] stackTrace, int httpStatus, String descriptionForUser) {
		this.stackTrace = stackTrace;
		this.httpStatus = httpStatus;
		this.descriptionForUser = descriptionForUser;
		this.descriptionForDebugger = null;
	}
}
