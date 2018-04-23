
package com.copeapp.servlet.survey;

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
import com.copeapp.dto.commons.SurveyDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Survey;
import com.copeapp.survey.SurveyRequestByIdDTO;
import com.copeapp.survey.SurveyResponseByIdDTO;
import com.copeapp.tomcat9Misc.StartupOperations;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SurveyById extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		SurveyRequestByIdDTO loginRequest = om.readValue(request.getInputStream(), SurveyRequestByIdDTO.class);		

		EntityManager entitymanager = StartupOperations.emfactory.createEntityManager();
		entitymanager.getTransaction().begin(); //dato che è una select la transaction è inutile
		Query query = entitymanager.createQuery("SELECT s FROM surveys s WHERE (s.surveyId = :surveyId) order by date(s.closeSurveyDate) desc ", Survey.class);
		query.setParameter("surveyId", loginRequest.getSurveyId());
		try {
				Survey s = (Survey) query.getSingleResult();
				ArrayList<RoleDTO> surveyVotersRoles = new ArrayList<RoleDTO>();	//create votersRoles
				RoleDTO tmp = new RoleDTO();
				for (Role r : s.getSurveyVotersRoles()) {
					BeanUtils.copyProperties(tmp, r);
					surveyVotersRoles.add(tmp);
				}
				ArrayList<RoleDTO> surveyViewersRoles = new ArrayList<RoleDTO>(); //create viewersRoles
				RoleDTO tmp1 = new RoleDTO();
				for (Role r : s.getSurveyViewersRoles()) {
					BeanUtils.copyProperties(tmp1, r);
					surveyVotersRoles.add(tmp1);
				}
				ArrayList<AnswerDTO> answers = new ArrayList<AnswerDTO>(); //create answer list
				AnswerDTO tmp2 = new AnswerDTO();
				for (Answer an : s.getAnswers()) {
					BeanUtils.copyProperties(tmp2, an);
					answers.add(tmp2);
				}
				User u = (User) s.getDeleteUser();	//al posto che lo user mando il username
				String username = u.getUsername();
				SurveyDTO surveyDTO = new SurveyDTO(s.getSurveyId(), s.getQuestion(), s.getCloseSurveyDate(), 10, surveyViewersRoles, surveyVotersRoles, username , s.getAnswersNumber(), answers);	
				
				om.writeValue(response.getOutputStream(), new SurveyResponseByIdDTO(surveyDTO));
				
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

