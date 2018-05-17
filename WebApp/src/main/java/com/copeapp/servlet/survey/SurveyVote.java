package com.copeapp.servlet.survey;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dao.survey.SurveyDAO;
import com.copeapp.dto.survey.SurveyRequestVoteDTO;
import com.copeapp.entities.common.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveyvote")
public class SurveyVote extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		User currentUser = UserDAO.selectByBasicAuthTokenException(request.getHeader("Authorization"));
		SurveyRequestVoteDTO surveyRequestVote = om.readValue(request.getInputStream(), SurveyRequestVoteDTO.class);						
		
		SurveyDAO.voteSurvey(currentUser, surveyRequestVote.getSurveyId(), surveyRequestVote.getAnswers());
		
		
	}
}
