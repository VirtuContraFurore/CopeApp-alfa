package com.copeapp.survey;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class SurveyResponseListDTO {
	
	@NonNull
	private ArrayList<SurveyResponseMiniDTO> surveyMini= new ArrayList<SurveyResponseMiniDTO>();
}
