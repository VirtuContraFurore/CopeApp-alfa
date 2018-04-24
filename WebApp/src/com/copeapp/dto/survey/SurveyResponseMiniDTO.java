package com.copeapp.dto.survey;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class SurveyResponseMiniDTO {
		
	private int surveyId;
	
	@NonNull
	private String question;
	
	@NonNull
	private Date closeSurveyDate;
	
	private int voters;
}
