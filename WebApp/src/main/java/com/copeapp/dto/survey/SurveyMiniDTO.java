package com.copeapp.dto.survey;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.NonFinal;

@Data
@AllArgsConstructor
public class SurveyMiniDTO {

	private int surveyId;
	
	@NonNull
	private String question;
	
	@NonNull
	private Date closeSurveyDate;
	
	private int voters;
}
