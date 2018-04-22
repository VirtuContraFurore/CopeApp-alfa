package com.copeapp.dto.commons;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SurveyDTO {

	private int surveyId;
	
	@NonNull
	private String question;
	
	@NonNull
	private Date closeSurveyDate;
	
	private int voters;
	
	@NonNull
	private List<RoleDTO> surveyViewersRoles;
	
	@NonNull
	private List<RoleDTO> surveyVotersRoles;
	
	@NonNull
	private String insertUsername;	//mandare solo lo username è più comodo
	
	private int answersNumber;
	
	@NonNull
	private List<AnswerDTO> answers;
}
