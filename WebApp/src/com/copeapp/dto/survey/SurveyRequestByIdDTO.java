package com.copeapp.dto.survey;

import lombok.Data;
import lombok.NonNull;

@Data
public class SurveyRequestByIdDTO {
	
	@NonNull
	private Integer surveyId;
}
