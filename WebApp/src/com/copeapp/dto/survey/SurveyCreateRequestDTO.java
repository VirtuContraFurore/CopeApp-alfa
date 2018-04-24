package com.copeapp.dto.survey;

import java.util.Date;
import java.util.List;

import com.copeapp.dto.commons.RoleDTO;
import com.sun.istack.internal.NotNull;

import lombok.Data;

@Data
public class SurveyCreateRequestDTO {
	
	@NotNull
	private String question = null;
	
	@NotNull
	private Date closeSurveyDate = null;
	
	@NotNull
	private List<RoleDTO> surveyViewersRoles = null;
	
	@NotNull
	private List<RoleDTO> surveyVotersRoles = null;
	
	@NotNull
	private List<AnswerDTO> answers = null;
	
	private Integer insertUserId = null;	//mandare solo lo username è più comodo
	
	private Integer answersNumber = null;

}
