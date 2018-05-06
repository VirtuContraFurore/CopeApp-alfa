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
import com.copeapp.dto.survey.SurveyMiniDTO;
import com.copeapp.dto.survey.SurveyRequestListDTO;
import com.copeapp.dto.survey.SurveyResponseListDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Survey;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
import com.copeapp.utilities.HttpStatusUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveyList")
public class SurveyList extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper om = new ObjectMapper();
		User currentUser = UserDAO.selectByBasicAuthTokenException(request.getHeader("Authorization"));
		SurveyRequestListDTO surveyListRequest = om.readValue(request.getInputStream(), SurveyRequestListDTO.class);
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();		
		Query query;
		
		if (!surveyListRequest.isMine()) {
			String keyword = (surveyListRequest.getKeyword().isEmpty()) ? "" : "LIKE ".concat(surveyListRequest.getKeyword());
			String active = (surveyListRequest.isActive()) ? ">" : "<=";
			query = entitymanager.createQuery("SELECT s FROM Survey s JOIN FETCH s.answers a WHERE (s.closeSurveyDate "+ active + 
					" current_timestamp) AND (s.openSurveyDate > current_timestamp) " + keyword + " ORDER BY s.closeSurveyDate desc ");
			query.setFirstResult(surveyListRequest.getLastSurveyNumber());
			query.setMaxResults(surveyListRequest.getNumberToRetrieve());
		} else {
			query = entitymanager.createQuery("SELECT s FROM Survey s JOIN FETCH s.answers a WHERE (s.insertUser.userId = :userId) ORDER BY s.closeSurveyDate desc");
			query.setParameter("userId" , currentUser.getUserId());
		}
		@SuppressWarnings("unchecked")
		List<Survey> surveys = query.getResultList();

		ArrayList<SurveyMiniDTO> miniDTO= new ArrayList<SurveyMiniDTO>();
		ArrayList<Role> commonRole = new ArrayList<Role>(currentUser.getRoles());
		for(Survey s : surveys) {
			commonRole.retainAll(s.getSurveyViewersRoles());
			if(!commonRole.isEmpty()) { //se lo user ha dei ruoli che intersecano quelli del survey... 
				int voteNumbers = 0;
				for (Answer a : s.getAnswers()) {
					voteNumbers += a.getVotesNumber();
				}
				miniDTO.add(new SurveyMiniDTO(s.getSurveyId(), s.getQuestion(), s.getCloseSurveyDate(), voteNumbers)); // il survey appare nella lista 
			}
		} 
		response.setStatus(HttpStatusUtility.OK);
		om.writeValue(response.getOutputStream(), new SurveyResponseListDTO(miniDTO));
	}
}
