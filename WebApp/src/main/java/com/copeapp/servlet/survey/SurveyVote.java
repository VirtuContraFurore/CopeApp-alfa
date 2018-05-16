package com.copeapp.servlet.survey;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.survey.AnswerDTO;
import com.copeapp.dto.survey.SurveyRequestVoteDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Survey;
import com.copeapp.entities.survey.Vote;
import com.copeapp.exception.SurveyExcption;
import com.copeapp.tomcat9Misc.DozerMapper;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
import com.copeapp.utilities.HttpStatusUtility;
import com.copeapp.utilities.MessageUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveyvote")
public class SurveyVote extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		User currentUser = UserDAO.selectByBasicAuthTokenException(request.getHeader("Authorization"));
		SurveyRequestVoteDTO surveyRequestVote = om.readValue(request.getInputStream(), SurveyRequestVoteDTO.class);						

		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		TypedQuery<Survey> query = entitymanager.createQuery("SELECT Survey FROM Survey s WHERE (s.surveyId = :surveyId) order by date(s.closeSurveyDate) DESC ", Survey.class);
		query.setParameter("surveyId", surveyRequestVote.getSurveyId());
		try {
			Survey survey = query.getSingleResult();
			ArrayList<Role> commonRole = new ArrayList<Role>(currentUser.getRoles());
			commonRole.retainAll(survey.getSurveyVotersRoles());
			if (!commonRole.isEmpty()) {
				for (AnswerDTO a: surveyRequestVote.getAnswers()) {
					new Vote(DozerMapper.getMapper().map(a, Answer.class), currentUser);
				}
			} else {
				throw new SurveyExcption(HttpStatusUtility.UNAUTHORIZED, MessageUtility.UNAUTHORIZED);
			}		
		} catch (NoResultException nre) {
			throw new SurveyExcption(HttpStatusUtility.NOT_FOUND, MessageUtility.SURVEY_NOT_FOUND, nre);
		}		
	}
}
