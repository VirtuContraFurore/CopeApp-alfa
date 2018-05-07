package com.copeapp.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
//	TODO aggiungere url per immagine e tipo domanda
	private Integer answerId; 
	
	@NonNull
	private String answerContent;
	
	@NonNull
	private Integer votesNumber;
}
