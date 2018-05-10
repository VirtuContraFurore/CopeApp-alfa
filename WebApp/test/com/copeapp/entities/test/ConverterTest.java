package com.copeapp.entities.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.copeapp.dto.survey.AnswerDTO;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Vote;
import com.copeapp.mappers.commons.GeneralMapper;

public class ConverterTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		HashMap<String, String> bindings = new HashMap<>();
//						dest		 sorg
		bindings.put("answerId", "votesNumber");
		ArrayList<Answer> list = new ArrayList<Answer>();
		ArrayList<Vote> votes = new ArrayList<Vote>();
		votes.add(new Vote());
		votes.add(new Vote());
		votes.add(new Vote());
		list.add(new Answer("1", 1, votes));
		list.add(new Answer("2", 2, votes));
		list.add(new Answer("3", 3, votes));
		List<Answer> a = new ArrayList<Answer>(list);
		GeneralMapper.convertList(a, AnswerDTO.class, bindings);
		
	}

}
