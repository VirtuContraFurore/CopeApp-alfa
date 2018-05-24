package com.copeapp.dao.survey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.copeapp.dto.survey.SurveyMiniDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Answer;
import com.copeapp.entities.survey.Survey;
import com.copeapp.entities.survey.Vote;
import com.copeapp.exception.SurveyExcption;
import com.copeapp.utilities.DozerMapper;
import com.copeapp.utilities.EntityManagerGlobal;
import com.copeapp.utilities.HttpStatusUtility;
import com.copeapp.utilities.MessageUtility;
import com.copeapp.utilities.MiscUtilities;

public class SurveyDAO {

	public static boolean hasVoted (User currentUser, int surveyId) {
		TypedQuery<Long> query;
		query = EntityManagerGlobal.getEntityManager().createQuery(
				"SELECT COUNT(DISTINCT v) FROM Vote v INNER JOIN v.answer a INNER JOIN  v.user u INNER JOIN a.survey s WHERE (s.surveyId = :surveyId) AND ((u.userId = :userId))",
				Long.class);
		query.setParameter("surveyId", surveyId);
		query.setParameter("userId", currentUser.getUserId());
		if(query.getSingleResult() != 0) {
			return true;
		} 
		return false;
		
	}
	
	public static Survey getSurveyById(int surveyId) {
		try {
			TypedQuery<Survey> query = EntityManagerGlobal.getEntityManager()
					.createQuery("SELECT DISTINCT s FROM Survey s WHERE (s.surveyId = :surveyId)", Survey.class);
			query.setParameter("surveyId", surveyId);
			return (Survey) query.getSingleResult();
		} catch (NoResultException nre) {
			throw new SurveyExcption(HttpStatusUtility.NOT_FOUND, MessageUtility.INVALID_ID + surveyId, nre);
		}
	}

	public static void surveyDelete(int surveyId, User currentUser) {
		try {
			Query query = EntityManagerGlobal.getEntityManager()
					.createQuery("SELECT DISTINCT Survey s WHERE s.insertUser = :currentUser");
			if ((MiscUtilities.checkRoles(currentUser.getRoles())) || (!query.getResultList().isEmpty())) {
				Date deleteDate = new Date(System.currentTimeMillis());
				Query queryDelete = EntityManagerGlobal.getEntityManager()
						.createQuery("UPDATE Survey s SET deleteDate = :deleteDate WHERE s.surveyId = :surveyId");
				queryDelete.setParameter("surveyId", surveyId);
				queryDelete.setParameter("deleteDate", deleteDate);
				queryDelete.executeUpdate();
			}
		} catch (NoResultException nre) {
			throw new SurveyExcption(HttpStatusUtility.NOT_FOUND, MessageUtility.INVALID_ID + surveyId, nre);
		}
	}

	public static void surveyCreate(User currentUser, Survey survey) {
		boolean canAccess = false;
		for (Role r : currentUser.getRoles()) {
			if (r.getRole().equalsIgnoreCase("redattore") || r.getRole().equalsIgnoreCase("admin")) {
				canAccess = true;
			}
		}
		if (canAccess) {
			survey.setVoters(0);
			EntityManagerGlobal.getEntityManager().persist(survey);

		} else {
			throw new SurveyExcption(HttpStatusUtility.UNAUTHORIZED, MessageUtility.UNAUTHORIZED);
		}
	}

	public static ArrayList<SurveyMiniDTO> getSurveyMiniDTO(User currentUser, int lastSurveyNumber,
		int numberToRetrieve, boolean isMine, String filterKey, boolean isActive) {
		TypedQuery<Survey> query;
		ArrayList<SurveyMiniDTO> miniDTO = new ArrayList<SurveyMiniDTO>();
		if (!isMine) {
			String keyword = (filterKey.isEmpty()) ? "" : filterKey;
			String active = "<"; //abbasso gli operatori ternari
			if(isActive) {
				active = ">";
			}
			if (keyword.isEmpty()) {
				query = EntityManagerGlobal.getEntityManager()
						.createQuery("SELECT DISTINCT s FROM Survey s JOIN FETCH s.answers a WHERE (s.closeSurveyDate " + active
								+ " current_timestamp) AND (s.openSurveyDate < current_timestamp) ORDER BY s.closeSurveyDate DESC",
								Survey.class);
			} else {
				query = EntityManagerGlobal.getEntityManager()
						.createQuery("SELECT DISTINCT s FROM Survey s JOIN FETCH s.answers a WHERE (s.closeSurveyDate " + active
								+ " current_timestamp) AND (s.openSurveyDate < current_timestamp) LIKE :keyword ORDER BY s.closeSurveyDate DESC",
								Survey.class);
				query.setParameter("keyword", keyword);
			}
		} else {
			query = EntityManagerGlobal.getEntityManager().createQuery(
					"SELECT DISTINCT s FROM Survey s JOIN FETCH s.answers a WHERE (s.insertUser.userId = :userId) ORDER BY s.closeSurveyDate DESC",
					Survey.class);
			query.setParameter("userId", currentUser.getUserId());
		}
		query.setFirstResult(lastSurveyNumber);
		query.setMaxResults(numberToRetrieve);
		List<Survey> surveys = query.getResultList();
		miniDTO = new ArrayList<SurveyMiniDTO>();
		for (Survey s : surveys) {
			if (MiscUtilities.checkRoles(currentUser.getRoles(), s.getSurveyViewersRoles())) {
				miniDTO.add(DozerMapper.getMapper().map(s, SurveyMiniDTO.class));
			}
		}
		return miniDTO;
	}

	public static void voteSurvey(User currentUser, int surveyId, List<Integer> answersId) {
		try {
			if (MiscUtilities.checkRoles(currentUser.getRoles(), getSurveyById(surveyId).getSurveyVotersRoles())) {
				TypedQuery<Answer> query = EntityManagerGlobal.getEntityManager()
						.createQuery("SELECT DISTINCT a From Answer a WHERE (a.answerId = :answerId)", Answer.class);
				ArrayList<Answer> votedAnswers = new ArrayList<Answer>();
				for (Integer aId : answersId) {
					query.setParameter("answerId", aId);
					votedAnswers.add(query.getSingleResult());
				}
				for (Answer a : votedAnswers) {
					EntityManagerGlobal.getEntityManager().persist(new Vote(a, currentUser));
				}
				Query queryAdd = EntityManagerGlobal.getEntityManager()
						.createQuery("UPDATE Survey s SET voters = s.voters + 1 WHERE s.surveyId = :surveyId");
				queryAdd.setParameter("surveyId", surveyId);
				queryAdd.executeUpdate();
			}
		} catch (NoResultException nre) {
			throw new SurveyExcption(HttpStatusUtility.NOT_FOUND, MessageUtility.SURVEY_NOT_FOUND, nre);
		}
	}
}
