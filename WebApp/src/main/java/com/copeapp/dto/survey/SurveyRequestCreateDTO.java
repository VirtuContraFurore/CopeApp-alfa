package com.copeapp.dto.survey;

import lombok.Data;
import lombok.NonNull;

@Data
public class SurveyRequestCreateDTO {
	@NonNull
	private SurveyDTO surveyDTO;

}
