package com.copeapp.dto.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class AnswerDTO {
	
	@NonNull
	private String answerContent;
	
	private int votesNumber;
}
