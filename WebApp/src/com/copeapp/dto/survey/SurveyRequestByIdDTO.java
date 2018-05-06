package com.copeapp.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class SurveyRequestByIdDTO {
	
	@NonNull
	private int surveyId;
}
