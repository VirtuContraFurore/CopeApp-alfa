package com.copeapp.dto.survey;

import lombok.Data;
import lombok.NonNull;

@Data
public class SurveyRequestCreateDTO {
	//TODO rimuovere surveyId cosicchè il client possa creare un nuovo survey (senza saperne l'id)
	@NonNull
	private SurveyDTO surveyDTO;

}
