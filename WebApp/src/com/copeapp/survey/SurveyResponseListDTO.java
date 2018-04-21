package com.copeapp.survey;

import java.util.ArrayList;

import lombok.Data;

@Data
public class SurveyResponseListDTO {

	private ArrayList<SurveyResponseMiniDTO> surveyMini= new ArrayList<SurveyResponseMiniDTO>();
}
