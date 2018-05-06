package com.copeapp.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
		
	private Integer answerId; 
	
	@NonNull
	private String answerContent;
	
	@NonNull
	private Integer votesNumber;
}
