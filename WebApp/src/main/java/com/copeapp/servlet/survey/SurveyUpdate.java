package com.copeapp.servlet.survey;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dao.survey.SurveyDAO;
import com.copeapp.dto.survey.SurveyDTO;
import com.copeapp.dto.survey.SurveyRequestCreateDTO;
import com.copeapp.dto.survey.SurveyResponseCreateDTO;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Survey;
import com.copeapp.exception.SurveyExcption;
import com.copeapp.utilities.DozerMapper;
import com.copeapp.utilities.HttpStatusUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveyupdate")
public class SurveyUpdate extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User currentUser = UserDAO.selectByBasicAuthTokenException(request.getHeader("Authorization"));
		
		ObjectMapper om = new ObjectMapper();
		
		SurveyRequestCreateDTO surveyRequest = om.readValue(request.getInputStream(), SurveyRequestCreateDTO.class);
		
		Survey survey = null;
		if (surveyRequest.getSurveyDTO().getSurveyId() != null && surveyRequest.getSurveyDTO().getSurveyId() != 0) {
			survey = DozerMapper.getMapper().map(surveyRequest.getSurveyDTO(), Survey.class);
			survey.setSurveyId(surveyRequest.getSurveyDTO().getSurveyId());
			for (Answer a : survey.getAnswers()) {
				a.setSurvey(survey);
			}
			SurveyDAO.surveyUpdate(currentUser, survey);
			om.writeValue(response.getOutputStream(), new SurveyResponseCreateDTO(DozerMapper.getMapper().map(survey, SurveyDTO.class)));
		} else {
			throw new SurveyExcption(HttpStatusUtility.BAD_REQUEST, "Non viene passato nessun identificatore");
		}
	}
}







