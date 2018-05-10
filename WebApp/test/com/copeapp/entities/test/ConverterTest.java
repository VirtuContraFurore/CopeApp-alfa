package com.copeapp.entities.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import com.copeapp.dto.survey.AnswerDTO;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Vote;
import com.copeapp.mappers.commons.GeneralMapper;

public class ConverterTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		ArrayList<Vote> votes = new ArrayList<Vote>();
		votes.add(new Vote());
		votes.add(new Vote());
		votes.add(new Vote());
		HashMap<String, String> bindings = new HashMap<>();
//						dest		 sorg
		bindings.put("answerId", "votesNumber");
		AnswerDTO answerDTO = (AnswerDTO)GeneralMapper.convert(new Answer("Ciao", 2102, votes), AnswerDTO.class, bindings);
		
	}

}
