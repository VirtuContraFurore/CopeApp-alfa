package com.copeapp.dao.survey;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.copeapp.dto.survey.AnswerDTO;
import com.copeapp.dto.survey.SurveyMiniDTO;
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

public class SurveyDAO {

	public static Survey getSurveyById (int surveyId) {
		EntityManager entityManager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			TypedQuery<Survey> query = entityManager.createQuery("FROM surveys s WHERE (s.surveyId = :surveyId)", Survey.class);
			query.setParameter("surveyId", surveyId);
			return (Survey) query.getSingleResult();
		} catch (NoResultException nre) {
			throw new SurveyExcption(HttpStatusUtility.NOT_FOUND, MessageUtility.INVALID_ID+surveyId, nre);
		} finally {
			entityManager.getTransaction().commit();
			entityManager.close();
		}
	}
	
	public static void surveyCreate(User currentUser, Survey survey) {
		EntityManager entityManager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			boolean canAccess = false;
			for (Role r : currentUser.getRoles()) {
				if (r.getRole().equalsIgnoreCase("redattore") || r.getRole().equalsIgnoreCase("admin")) {	//controllo se l'utente può creare survey
					canAccess=true;
				}
			}
			if (canAccess) {
				entityManager.persist(survey);
				
			} else {
				throw new SurveyExcption(HttpStatusUtility.UNAUTHORIZED, MessageUtility.UNAUTHORIZED);
			}
		} finally {
			entityManager.getTransaction().commit();
			entityManager.close();
		}
	}
	
	public static ArrayList<SurveyMiniDTO> getSurveyMiniDTO(User currentUser, int lastSurveyNumber, int numberToRetrieve, boolean isMine, String filterKey, boolean isActive) {
		EntityManager entityManager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		entityManager.getTransaction().begin();
		TypedQuery<Survey> query;
		ArrayList<SurveyMiniDTO> miniDTO= new ArrayList<SurveyMiniDTO>();
		try {
			if (!isMine) {
				String keyword = (filterKey.isEmpty()) ? "" : filterKey;
				String active = (isActive) ? ">" : "<=";
				if (keyword.isEmpty()) {
					query = entityManager.createQuery("FROM Survey s JOIN FETCH s.answers a WHERE (s.closeSurveyDate "+active+" current_timestamp) AND (s.openSurveyDate > current_timestamp) ORDER BY s.closeSurveyDate DESC", Survey.class);
				} else {
					query = entityManager.createQuery("FROM Survey s JOIN FETCH s.answers a WHERE (s.closeSurveyDate "+active+" current_timestamp) AND (s.openSurveyDate > current_timestamp) LIKE :keyword ORDER BY s.closeSurveyDate DESC", Survey.class);
					query.setParameter("keyword", keyword);
				}
				query.setFirstResult(lastSurveyNumber);
				query.setMaxResults(numberToRetrieve);
			} else {
				query = entityManager.createQuery("FROM Survey s JOIN FETCH s.answers a WHERE (s.insertUser.userId = :userId) ORDER BY s.closeSurveyDate DESC", Survey.class);
				query.setParameter("userId" , currentUser.getUserId());
			}
			List<Survey> surveys = query.getResultList();
			miniDTO= new ArrayList<SurveyMiniDTO>();
			ArrayList<Role> commonRole = new ArrayList<Role>(currentUser.getRoles());
			for(Survey s : surveys) {
				commonRole.retainAll(s.getSurveyViewersRoles());
				if(!commonRole.isEmpty()) { //se lo user ha dei ruoli che intersecano quelli del survey... 
					int voteNumbers = 0;
					for (Answer a : s.getAnswers()) {
						voteNumbers += a.getVotesNumber();
					}
					miniDTO.add(DozerMapper.getMapper().map(s, SurveyMiniDTO.class));
					miniDTO.get(miniDTO.size()-1).setVoters(voteNumbers);
				}
			}
		} finally {
			entityManager.getTransaction().commit();
			entityManager.close();
		}
		return miniDTO;
		
	}
	
	public static void voteSurvey(User currentUser, int surveyId, List<AnswerDTO> answers) {
		
		EntityManager entityManager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		entityManager.getTransaction().begin();
		TypedQuery<Survey> query = entityManager.createQuery("SELECT Survey FROM Survey s WHERE (s.surveyId = :surveyId) order by date(s.closeSurveyDate) DESC ", Survey.class);
		query.setParameter("surveyId", surveyId);
		try {
			Survey survey = query.getSingleResult();
			ArrayList<Role> commonRole = new ArrayList<Role>(currentUser.getRoles());
			commonRole.retainAll(survey.getSurveyVotersRoles());
			if (!commonRole.isEmpty()) {
				for (AnswerDTO a: answers) {
					Vote v = new Vote(DozerMapper.getMapper().map(a, Answer.class), currentUser);
					entityManager.persist(v);
				}
			} else {
				throw new SurveyExcption(HttpStatusUtility.UNAUTHORIZED, MessageUtility.UNAUTHORIZED);
			}		
		} catch (NoResultException nre) {
			throw new SurveyExcption(HttpStatusUtility.NOT_FOUND, MessageUtility.SURVEY_NOT_FOUND, nre);
		} finally {
			entityManager.getTransaction().commit();
			entityManager.close();
		}
	}
}
