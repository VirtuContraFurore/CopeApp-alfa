package com.copeapp.dto.survey;

import lombok.Data;
import lombok.NonNull;

@Data
public class AnswerDTO {
	
	@NonNull
	private Integer answerId; 
	
	@NonNull
	private String answerContent;
	
	@NonNull
	private Integer votesNumber;
}
