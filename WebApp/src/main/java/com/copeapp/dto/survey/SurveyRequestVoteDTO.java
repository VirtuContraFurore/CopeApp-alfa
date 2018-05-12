package com.copeapp.dto.survey;

import java.util.ArrayList;

import lombok.Data;
import lombok.NonNull;

@Data
public class SurveyRequestVoteDTO {
	
	@NonNull
	private Integer surveyId;
	
	@NonNull
	private ArrayList<AnswerDTO> answers;
}
