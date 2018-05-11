package com.copeapp.mappers.survey;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.copeapp.dto.survey.SurveyDTO;
import com.copeapp.entities.survey.Survey;

@Mapper(uses = AnswerMapper.class)
public interface SurveyMapper {
	/*
	@Mappings({	
		@Mapping( target = "deleteDate", ignore = true),
		@Mapping( target = "deleteUser", ignore = true)
	})
	//SurveyDTO surveyToSurveyDTO(Survey survey);
	
	@Mappings({
		@Mapping(target = "surveyId", ignore = true),
		@Mapping( target = "deleteDate", ignore = true),
		@Mapping( target = "deleteUser", ignore = true),
		@Mapping( target = "voters", ignore = true)
	})
	//Survey surveyDTOtoSurvey(SurveyDTO surveyDTO);
	*/
}
