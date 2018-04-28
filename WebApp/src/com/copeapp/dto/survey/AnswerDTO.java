package com.copeapp.dto.survey;

import com.sun.istack.internal.NotNull;

import lombok.Data;

@Data
public class AnswerDTO {
	
	@NotNull
	private int answerId; 
	
	@NotNull
	private String answerContent = null;
	
	private Integer votesNumber = null;
}
