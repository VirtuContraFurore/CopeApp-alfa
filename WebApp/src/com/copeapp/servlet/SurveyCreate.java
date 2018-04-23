package com.copeapp.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.copeapp.dto.commons.AnswerDTO;
import com.copeapp.dto.commons.GenericErrorDTO;
import com.copeapp.dto.commons.RoleDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Survey;
import com.copeapp.survey.SurveyCreateRequestDTO;
import com.copeapp.tomcat9Misc.StartupOperations;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveycreate")
public class SurveyCreate extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		SurveyCreateRequestDTO surveyUpdateRequest = om.readValue(request.getInputStream(), SurveyCreateRequestDTO.class);		
		try {	//creare entity da mettere nel db
			ArrayList<Role> viewersRoles = new ArrayList<Role>();
			Role tmp = new Role();
			for (RoleDTO r : surveyUpdateRequest.getSurveyViewersRoles()) {
				BeanUtils.copyProperties(tmp, r);
				viewersRoles.add(tmp);
			}
			ArrayList<Role> votersRoles = new ArrayList<Role>();
			Role tmp1 = new Role();
			for (RoleDTO r : surveyUpdateRequest.getSurveyVotersRoles()) {
				BeanUtils.copyProperties(tmp, r);
				votersRoles.add(tmp);
			}
			ArrayList<Answer> answer = new ArrayList<Answer>();
			Answer tmp2 = new Answer();
			for (AnswerDTO a : surveyUpdateRequest.getAnswers()) {
				BeanUtils.copyProperties(tmp2, a);
				answer.add(tmp2);
			}
			//manca lo user e la data di inserimento
			Survey survey = new Survey(surveyUpdateRequest.getQuestion(), surveyUpdateRequest.getCloseSurveyDate(), new Date(), new User(), votersRoles, viewersRoles, answer);
			//non ho idea di come si faccia la create sul db
		} catch (NoResultException nre) {
			GenericErrorDTO errorResponse = new GenericErrorDTO(nre.getStackTrace(), 401, "Utente non trovato");
			response.setStatus(401);
			om.writeValue(response.getOutputStream(), errorResponse);
		} catch (IllegalAccessException e) {
			GenericErrorDTO errorResponse = new GenericErrorDTO(e.getStackTrace(), 500, "Acceso al database negato");
			e.printStackTrace();
			om.writeValue(response.getOutputStream(), errorResponse);
		} catch (InvocationTargetException e) {
			GenericErrorDTO errorResponse = new GenericErrorDTO(e.getStackTrace(), 500, "Errore interno al server");
			e.printStackTrace();
			om.writeValue(response.getOutputStream(), errorResponse);
		}
		EntityManager entitymanager = StartupOperations.emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.getTransaction().commit();
		entitymanager.close();
	}
}
