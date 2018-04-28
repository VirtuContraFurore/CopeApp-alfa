package com.copeapp.servlet.survey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.survey.SurveyRequestListDTO;
import com.copeapp.dto.survey.SurveyResponseListDTO;
import com.copeapp.dto.survey.SurveyResponseMiniDTO;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Survey;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveyList")
public class SurveyList extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		User currentUser = UserDAO.selectByBasicAuthTokenException(request.getHeader("Authorization"));
		SurveyRequestListDTO surveyListRequest = om.readValue(request.getInputStream(), SurveyRequestListDTO.class);		
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		entitymanager.getTransaction().begin(); //dato che è una select la transaction è inutile
		Query query;
		/* TODO 
		 * gestire le keyword
		 * gestire visibilità dei sondaggi */
		if	(surveyListRequest.isActive()) {
			query = entitymanager.createQuery("SELECT s FROM Survey s JOIN FETCH s.answers a WHERE (s.closeSurveyDate > current_timestamp) order by s.closeSurveyDate desc ", Survey.class);
		} else {
			query = entitymanager.createQuery("SELECT s FROM Survey s JOIN FETCH s.answers a WHERE (s.closeSurveyDate <= current_timestamp) order by s.closeSurveyDate desc ", Survey.class);
		}
		query.setFirstResult(surveyListRequest.getLastSurveyNumber());
		query.setMaxResults(surveyListRequest.getNumberToRetrieve());
		@SuppressWarnings("unchecked")
		List<Survey> survey = query.getResultList();
		ArrayList<SurveyResponseMiniDTO> miniDTO= new ArrayList<SurveyResponseMiniDTO>();
		for (Survey s : survey) {
			int voteNumbers = 0;
			for (Answer a : s.getAnswers()) {
				voteNumbers += a.getVotesNumber();
			}
			SurveyResponseMiniDTO tmp = new SurveyResponseMiniDTO(s.getSurveyId(), s.getQuestion(), s.getCloseSurveyDate(), voteNumbers);
			miniDTO.add(tmp);
		}
		om.writeValue(response.getOutputStream(), new SurveyResponseListDTO(miniDTO));
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}
}
