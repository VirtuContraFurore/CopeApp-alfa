package com.copeapp.servlet.survey;

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

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.commons.ExceptionDTO;
import com.copeapp.dto.commons.RoleDTO;
import com.copeapp.dto.survey.AnswerDTO;
import com.copeapp.dto.survey.SurveyCreateRequestDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Survey;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
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
			ArrayList<Answer> answers = new ArrayList<Answer>();
			Answer tmp2 = new Answer();
			for (AnswerDTO a : surveyUpdateRequest.getAnswers()) {
				BeanUtils.copyProperties(tmp2, a);
				answers.add(tmp2);
			}
			Survey survey = new Survey(surveyUpdateRequest.getQuestion(), surveyUpdateRequest.getCloseSurveyDate(), surveyUpdateRequest.getCreationDate(), currentUser, votersRoles, viewersRoles, answers);
			entitymanager.getTransaction().begin();
			entitymanager.persist(survey);
			entitymanager.getTransaction().commit();
			entitymanager.close();
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			ExceptionDTO errorResponse = new ExceptionDTO(e, 500, "Acceso al database negato");
			e.printStackTrace();
			om.writeValue(response.getOutputStream(), errorResponse);
		}
	}
}
