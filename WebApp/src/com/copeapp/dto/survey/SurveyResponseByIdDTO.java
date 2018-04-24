package com.copeapp.dto.survey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SurveyResponseByIdDTO {
	
	@NonNull
	private SurveyDTO surveyDTO;
}
