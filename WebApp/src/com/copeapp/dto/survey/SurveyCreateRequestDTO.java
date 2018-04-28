package com.copeapp.dto.survey;

import java.util.Date;
import java.util.List;

import com.copeapp.dto.commons.RoleDTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class SurveyCreateRequestDTO {
	
	@NonNull
	private String question = null;
	
	@NonNull
	private Date closeSurveyDate = null;
	
	@NonNull
	private List<RoleDTO> surveyViewersRoles = null;
	
	@NonNull
	private List<RoleDTO> surveyVotersRoles = null;
	
	@NonNull
	private List<AnswerDTO> answers = null;
	
	private Integer insertUserId = null;	//mandare solo lo username è più comodo
	
	private Integer answersNumber = null;

}
