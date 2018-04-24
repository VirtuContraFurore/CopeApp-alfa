package com.copeapp.dto.commons;

import lombok.Data;

@Data
public class ExceptionDTO {

	private StackTraceElement[] stackTrace = null;
	private Integer httpStatus = null;
	private String descrptionForUser = null;
	private String debuggingDescription = null;
	
	public ExceptionDTO(StackTraceElement[] stackTrace, int httpStatus, String descrptionForUser, String debuggingDescription) {
		
		this.stackTrace = stackTrace;
		this.httpStatus = httpStatus;
		this.descrptionForUser = descrptionForUser;
		this.debuggingDescription = (debuggingDescription == null)? (descrptionForUser):(debuggingDescription);
	}
	
	public ExceptionDTO(StackTraceElement[] stackTrace, int httpStatus, String descrptionForUser) {
		
		this.stackTrace = stackTrace;
		this.httpStatus = httpStatus;
		this.descrptionForUser = descrptionForUser;
		this.debuggingDescription = descrptionForUser;
	}
}
