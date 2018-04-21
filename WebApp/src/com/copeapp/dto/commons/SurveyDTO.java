package com.copeapp.dto.commons;

import java.util.List;

import com.copeapp.dto.commons.AnswerDTO;
import com.copeapp.survey.SurveyResponseMiniDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class SurveyDTO {

private SurveyResponseMiniDTO miniDTO;
	
	@NonNull
	private String description;
	
	@NonNull
	private List<RoleDTO> surveyViewersRoles;
	
	@NonNull
	private List<RoleDTO> surveyVotersRoles;
	
	@NonNull
	private UserDTO insertUser;
	
	private int answersNumber;
	
	@NonNull
	private List<AnswerDTO> answers;
}
