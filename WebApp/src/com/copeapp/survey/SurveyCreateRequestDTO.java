package com.copeapp.survey;

import java.util.Date;
import java.util.List;

import com.copeapp.dto.commons.AnswerDTO;
import com.copeapp.dto.commons.RoleDTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class SurveyCreateRequestDTO {
	
	@NonNull
	private String question;
	
	@NonNull
	private Date closeSurveyDate;
	
	@NonNull
	private List<RoleDTO> surveyViewersRoles;
	
	@NonNull
	private List<RoleDTO> surveyVotersRoles;
	
	@NonNull
	private List<AnswerDTO> answers;
	
	private int insertUserId;	//mandare solo lo username è più comodo
	
	private int answersNumber;

}
