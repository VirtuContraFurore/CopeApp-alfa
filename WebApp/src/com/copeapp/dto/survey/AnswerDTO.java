package com.copeapp.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerDTO {
	
	@NonNull
	private String answerContent;
	
	private int votesNumber;
}
