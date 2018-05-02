package com.copeapp.servlet.survey;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.commons.RoleDTO;
import com.copeapp.dto.survey.AnswerDTO;
import com.copeapp.dto.survey.SurveyRequestVoteDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Survey;
import com.copeapp.entities.survey.Vote;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveyvote")
public class SurveyVote extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		User currentUser = UserDAO.selectByBasicAuthTokenException(request.getHeader("Authorization"));
		SurveyRequestVoteDTO surveyRequestVote = om.readValue(request.getInputStream(), SurveyRequestVoteDTO.class);						

		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		entitymanager.getTransaction().begin();
		Query query = entitymanager.createQuery("SELECT s FROM surveys s WHERE (s.surveyId = :surveyId) order by date(s.closeSurveyDate) desc ", Survey.class);
		query.setParameter("surveyId", surveyRequestVote.getSurveyId());
		Survey survey = (Survey) query.getSingleResult();
		try {
			ArrayList<RoleDTO> surveyVotersRoles = new ArrayList<RoleDTO>();	//create votersRoles		
			RoleDTO tmp = new RoleDTO();
			for (Role r : survey.getSurveyVotersRoles()) {
				BeanUtils.copyProperties(tmp, r);
				surveyVotersRoles.add(tmp);
			}
			ArrayList<RoleDTO> commonRole = new ArrayList<RoleDTO>(surveyVotersRoles);
			commonRole.retainAll(currentUser.getRoles());
			//isAllowed = (commonRole.isEmpty())? false : true; molto utile

			if (!commonRole.isEmpty()) {
				ArrayList<Answer> answer = new ArrayList<Answer>();
				Answer tmp2 = new Answer();
				for (AnswerDTO a : surveyRequestVote.getAnswers()) {
					BeanUtils.copyProperties(tmp2, a);
					answer.add(tmp2);
				}
				for (Answer a : answer) {
					Vote v = new Vote(a, currentUser);
					entitymanager.persist(v);
				}
			} else {
				//lanciare eccezione surveyException (da creare) cosicché il webFilter la catchi
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			//chiamare l'eccezione surveyException e lasciare fare al filtro il resto
			e.printStackTrace();
		}
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}
}
