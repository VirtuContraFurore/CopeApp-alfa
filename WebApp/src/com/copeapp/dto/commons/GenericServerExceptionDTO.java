package com.copeapp.dto.commons;
 
public class GenericServerExceptionDTO {

	private StackTraceElement[] stackTrace;
	private int httpStatus;
	private String description;
	
	public StackTraceElement[] getStackTrace() {
		return stackTrace;
	}
	public void setStackTrace(StackTraceElement[] stackTrace) {
		this.stackTrace = stackTrace;
	}
	public int getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public GenericServerExceptionDTO(int httpStatus, String description) {
		super();
		this.httpStatus = httpStatus;
		this.description = description;
	}
	public GenericServerExceptionDTO(StackTraceElement[] stackTrace, int httpStatus, String description) {
		super();
		this.stackTrace = stackTrace;
		this.httpStatus = httpStatus;
		this.description = description;
	}
	public GenericServerExceptionDTO() {
		super();
	}
	
	
}
