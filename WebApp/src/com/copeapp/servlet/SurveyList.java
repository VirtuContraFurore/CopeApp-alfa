package com.copeapp.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.copeapp.dto.commons.AnswerDTO;
import com.copeapp.dto.commons.GenericErrorDTO;
import com.copeapp.dto.commons.RoleDTO;
import com.copeapp.entities.Role;
import com.copeapp.entities.Survey;
import com.copeapp.survey.SurveyRequestListDTO;
import com.copeapp.survey.SurveyResponseListDTO;
import com.copeapp.tomcat9Misc.StartupOperations;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SurveyList extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		SurveyRequestListDTO loginRequest = om.readValue(request.getInputStream(), SurveyRequestListDTO.class);		
		SurveyResponseListDTO loginResponse = new SurveyResponseListDTO();
		
		EntityManager entitymanager = StartupOperations.emfactory.createEntityManager();
		entitymanager.getTransaction().begin(); //dato che è una select la transaction è inutile
		Query query = entitymanager.createQuery("SELECT s FROM Survey u WHERE (u.closeSurveyDate > current_date) order by date(u.closeSurveyDate) desc ", Survey.class);
		query.setMaxResults(5);
		ArrayList<Survey> survey = new ArrayList<Survey>();
		try {
		survey = (ArrayList<Survey>) query.getResultList();
		for (Survey s : survey) { //per ogni elemento aggiungi una votersRole
			ArrayList<RoleDTO> surveyVotersRoles = new ArrayList<RoleDTO>();
			RoleDTO tmp = new RoleDTO();
			for (Role r : s.getSurveyVotersRoles()) {
				BeanUtils.copyProperties(tmp, r);
				surveyVotersRoles.add(tmp);
			}
			ArrayList<RoleDTO> surveyViewersRoles = new ArrayList<RoleDTO>();
			RoleDTO tmp1 = new RoleDTO();
			for (Role r : s.getSurveyViewersRoles()) {
				BeanUtils.copyProperties(tmp1, r);
				surveyVotersRoles.add(tmp1);
			}
			ArrayList<AnswerDTO> tmp2 = new ArrayList<AnswerDTO>();
			
		}
		} catch  (NoResultException nre) {
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
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}
}
