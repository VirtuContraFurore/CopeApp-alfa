package com.copeapp.mappers.survey;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.copeapp.dto.survey.AnswerDTO;
import com.copeapp.entities.survey.Answer;

@Mapper
public interface AnswerMapper {
	
	/*
	@Mappings({
		@Mapping(target = "votes", ignore = true)
	})	
	AnswerDTO answerToAnswerDTO(Answer answer);
	
	@Mappings({
		@Mapping(target = "answerId", ignore = true),
		@Mapping(target = "votes", ignore = true)
	})	
	Answer answerDTOtoAnswer(AnswerDTO answerDTO);
	*/
}
