package com.copeapp.dto.survey;

import lombok.Data;
import lombok.NonNull;

@Data
public class SurveyRequestDeleteDTO {
	@NonNull
	private Integer surveyId;
}
