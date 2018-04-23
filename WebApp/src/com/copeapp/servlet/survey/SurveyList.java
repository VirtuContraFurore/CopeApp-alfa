package com.copeapp.servlet.survey;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dto.commons.GenericServerExceptionDTO;
import com.copeapp.entities.survey.Survey;
import com.copeapp.survey.SurveyRequestListDTO;
import com.copeapp.survey.SurveyResponseListDTO;
import com.copeapp.survey.SurveyResponseMiniDTO;
import com.copeapp.tomcat9Misc.StartupOperations;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveylist")
public class SurveyList extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		SurveyRequestListDTO surveyListRequest = om.readValue(request.getInputStream(), SurveyRequestListDTO.class);		
		
		EntityManager entitymanager = StartupOperations.emfactory.createEntityManager();
		entitymanager.getTransaction().begin(); //dato che è una select la transaction è inutile
		Query query;
		if	(surveyListRequest.isTipo()) {
			query = entitymanager.createQuery("SELECT s FROM surveys u WHERE (s.closeSurveyDate > current_date) order by date(s.closeSurveyDate) desc ", Survey.class);
		} else {
			query = entitymanager.createQuery("SELECT s FROM surveys u WHERE (s.closeSurveyDate < current_date) order by date(s.closeSurveyDate) desc ", Survey.class);
		}
		query.setMaxResults(5);
		//Survey survey = new Survey();
		try {
		ArrayList<Survey> survey = (ArrayList<Survey>) query.getResultList();
		ArrayList<SurveyResponseMiniDTO> miniDTO= new ArrayList<SurveyResponseMiniDTO>();
		for (Survey s : survey) {
			//contare il numero di persone votanti
			SurveyResponseMiniDTO tmp = new SurveyResponseMiniDTO(s.getSurveyId(), s.getQuestion(), s.getCloseSurveyDate(), 10);
			miniDTO.add(tmp);
		}
		
		om.writeValue(response.getOutputStream(), new SurveyResponseListDTO(miniDTO));
		} catch (NoResultException nre) {
			GenericServerExceptionDTO errorResponse = new GenericServerExceptionDTO(nre.getStackTrace(), 401, "Utente non trovato");
			response.setStatus(401);
			om.writeValue(response.getOutputStream(), errorResponse);
		}
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}
}
