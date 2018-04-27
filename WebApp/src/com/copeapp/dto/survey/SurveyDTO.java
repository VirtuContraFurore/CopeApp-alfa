package com.copeapp.dto.survey;

import java.util.Date;
import java.util.List;

import com.copeapp.dto.commons.RoleDTO;
import com.sun.istack.internal.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SurveyDTO {

	private Integer surveyId = null;
	
	@NotNull
	private String question = null;
	
	@NotNull
	private Date closeSurveyDate = null;
	
	private Integer voters = null;
	
	@NotNull
	private List<RoleDTO> surveyViewersRoles = null;
	
	@NotNull
	private List<RoleDTO> surveyVotersRoles = null;
	
	@NotNull
	private String insertUsername = null; //mandare solo lo username è più comodo
	
	private Integer answersNumber = null;
	
	@NotNull
	private List<AnswerDTO> answer = null;
}
