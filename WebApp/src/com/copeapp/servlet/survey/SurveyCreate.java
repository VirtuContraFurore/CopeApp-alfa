package com.copeapp.servlet.survey;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.commons.RoleDTO;
import com.copeapp.dto.survey.AnswerDTO;
import com.copeapp.dto.survey.SurveyCreateRequestDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Survey;
import com.copeapp.exception.SurveyRequestFailedExcption;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
import com.copeapp.utilities.HttpStatusUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveycreate")
public class SurveyCreate extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User currentUser = UserDAO.selectByBasicAuthTokenException(request.getHeader("Authorization"));
		ObjectMapper om = new ObjectMapper();
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		SurveyCreateRequestDTO surveyUpdateRequest = om.readValue(request.getInputStream(), SurveyCreateRequestDTO.class);		
		
		try {
			//TODO controllare se l'utente può creare surveys
			ArrayList<Role> viewersRoles = new ArrayList<Role>();
			Role tmp = new Role();
			for (RoleDTO r : surveyUpdateRequest.getSurveyViewersRoles()) {
				BeanUtils.copyProperties(tmp, r);
				viewersRoles.add(tmp);
			}
			
			ArrayList<Role> votersRoles = new ArrayList<Role>();
			Role tmp1 = new Role();
			for (RoleDTO r : surveyUpdateRequest.getSurveyVotersRoles()) {
				BeanUtils.copyProperties(tmp1, r);
				votersRoles.add(tmp1);
			}
			
			ArrayList<Answer> answers = new ArrayList<Answer>();
			Answer tmp2 = new Answer();
			for (AnswerDTO a : surveyUpdateRequest.getAnswers()) {
				BeanUtils.copyProperties(tmp2, a);
				answers.add(tmp2);
			}
// 			^^^ controllare sabato ^^^
			Survey survey = new Survey(surveyUpdateRequest.getQuestion(), surveyUpdateRequest.getAnswersNumber(), 
					surveyUpdateRequest.getCloseSurveyDate(), surveyUpdateRequest.getCreationDate(), currentUser, 
					votersRoles, viewersRoles, answers);
			entitymanager.getTransaction().begin();
			entitymanager.persist(survey);
			entitymanager.getTransaction().commit();
			entitymanager.close();
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new SurveyRequestFailedExcption(HttpStatusUtility.INTERNAL_SERVER_ERROR, "Errore interno al server", e);
		}
	}
}
