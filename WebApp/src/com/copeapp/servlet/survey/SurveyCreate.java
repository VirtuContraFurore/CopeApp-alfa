package com.copeapp.servlet.survey;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mapstruct.factory.Mappers;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.survey.SurveyRequestCreateDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Survey;
import com.copeapp.mappers.survey.SurveyMapper;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveycreate")
public class SurveyCreate extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User currentUser = UserDAO.selectByBasicAuthTokenException(request.getHeader("Authorization"));
		ObjectMapper om = new ObjectMapper();
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		SurveyRequestCreateDTO SurveyDTO = om.readValue(request.getInputStream(), SurveyRequestCreateDTO.class);		

		boolean canAccess = false;
		for (Role r : currentUser.getRoles()) {
			if (r.getDescription()=="redattore" || r.getDescription()=="admin") {	//controllo se l'utente può creare survey
				canAccess=true;
			}
		}
		if (canAccess) {
			Survey survey = Mappers.getMapper(SurveyMapper.class).surveyDTOtoSurvey(SurveyDTO.getSurveyDTO());  
			//TODO non sono sicuro sia giusta come cosa
			entitymanager.getTransaction().begin();
			entitymanager.persist(survey);
			entitymanager.getTransaction().commit();
			entitymanager.close();			
		}
	}
}
