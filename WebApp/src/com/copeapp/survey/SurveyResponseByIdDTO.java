package com.copeapp.survey;

import com.copeapp.dto.commons.SurveyDTO;

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
