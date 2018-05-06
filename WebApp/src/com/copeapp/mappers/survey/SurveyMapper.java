package com.copeapp.mappers.survey;

import org.mapstruct.Mapper;

import com.copeapp.dto.survey.SurveyDTO;
import com.copeapp.entities.survey.Survey;

@Mapper(uses = AnswerMapper.class)
public interface SurveyMapper {
	
	SurveyDTO surveyToSurveyDTO(Survey survey);
	
	Survey surveyDTOtoSurvey(SurveyDTO surveyDTO);
	
}
