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

		/* TODO aggiungere alla query se � mio */
		//TODO in versione 2.0 multiple filters
		/*puoi fare cosi
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entitymanager);
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Survey.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword().onField("question").matching(surveyListRequest.getKeyword()).createQuery();
		query = fullTextEntityManager.createFullTextQuery(luceneQuery, Survey.class);			
		puoi fare cos�
		query = entitymanager.createQuery("SELECT s FROM Survey s WHERE to_tsvector(s.question) @@ to_tsquery(':keyword')", Survey.class);
		query.setParameter("keyword", surveyListRequest.getKeyword());*/
		/*	if(surveyListRequest.getKeyword().isEmpty()) {
			if	(surveyListRequest.isActive()) {
				query = entitymanager.createQuery("SELECT s FROM Survey s JOIN FETCH s.answers a WHERE (s.closeSurveyDate > current_timestamp) ORDER BY s.closeSurveyDate desc ", Survey.class);
			} else {
				query = entitymanager.createQuery("SELECT s FROM Survey s JOIN FETCH s.answers a WHERE (s.closeSurveyDate <= current_timestamp) ORDER BY s.closeSurveyDate desc ", Survey.class);
			}
		} else {

			query = entitymanager.createQuery("SELECT s FROM Survey s WHERE s.question LIKE :keyword ORDER BY s.closeSurveyDate DESC", Survey.class);
			query.setParameter("keyword", surveyListRequest.getKeyword());

		}*/

		//implementazione più cazzuta della query (come piace a gallo)
		String keyword = (surveyListRequest.getKeyword().isEmpty())? "" : "LIKE ".concat(surveyListRequest.getKeyword());	
		String active = (surveyListRequest.isActive())? ">": "<=";	
		
		Query query = entitymanager.createQuery("SELECT s FROM Survey s JOIN FETCH s.answers a WHERE (s.closeSurveyDate "+active+" current_timestamp) "+keyword+" ORDER BY s.closeSurveyDate desc ");
		query.setFirstResult(surveyListRequest.getLastSurveyNumber());
		query.setMaxResults(surveyListRequest.getNumberToRetrieve());
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
			} else {
				//TODO forse lanciare eccezione?
			}
		} 
		response.setStatus(HttpStatusUtility.OK);
		om.writeValue(response.getOutputStream(), new SurveyResponseListDTO(miniDTO));
	}
}
