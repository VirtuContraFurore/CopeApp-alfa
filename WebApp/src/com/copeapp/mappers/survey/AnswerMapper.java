package com.copeapp.mappers.survey;

import org.mapstruct.Mapper;

import com.copeapp.dto.survey.AnswerDTO;
import com.copeapp.entities.survey.Answer;

@Mapper
public interface AnswerMapper {
	
	AnswerDTO answerToAnswerDTO(Answer answer);
	
	Answer answerDTOtoAnswer(AnswerDTO answerDTO);
	
}
