package com.copeapp.dao.survey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		return EntityManagerGlobal.getEntityManager().find(Survey.class, surveyId);
	}

	public static void surveyDelete(int surveyId, User currentUser) {
		Survey survey = EntityManagerGlobal.getEntityManager().find(Survey.class, surveyId);
		if (survey == null) {
			throw new SurveyExcption(HttpStatusUtility.BAD_REQUEST, "Ci hai provato e hai fallito miseramente, BESTIA!");
		}
		if (MiscUtilities.isAdmin(currentUser.getRoles()) || survey.getInsertUser().equals(currentUser)) {
			survey.setDeleteDate(new Date());
			survey.setDeleteUser(currentUser);
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
								+ " current_timestamp AND s.openSurveyDate < current_timestamp AND s.deleteDate is null) ORDER BY s.closeSurveyDate DESC",
								Survey.class);
			} else {
				query = EntityManagerGlobal.getEntityManager()
						.createQuery("SELECT DISTINCT s FROM Survey s JOIN FETCH s.answers a WHERE (s.closeSurveyDate " + active
								+ " current_timestamp AND s.openSurveyDate < current_timestamp AND s.deleteDate is null) LIKE :keyword ORDER BY s.closeSurveyDate DESC",
								Survey.class);
				query.setParameter("keyword", keyword);
			}
		} else {
			query = EntityManagerGlobal.getEntityManager().createQuery(
					"SELECT DISTINCT s FROM Survey s JOIN FETCH s.answers a WHERE (s.insertUser.userId = :userId AND s.deleteDate is null) ORDER BY s.closeSurveyDate DESC",
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
		Survey survey = getSurveyById(surveyId);
		if (survey == null) {
			throw new SurveyExcption(HttpStatusUtility.NOT_FOUND, MessageUtility.SURVEY_NOT_FOUND);
		}
		if (MiscUtilities.checkRoles(currentUser.getRoles(), survey.getSurveyVotersRoles())) {
			for (Integer aId : answersId) {
				Answer answer = EntityManagerGlobal.getEntityManager().find(Answer.class, aId);
				if (answer == null) {
					throw new SurveyExcption(HttpStatusUtility.NOT_FOUND, MessageUtility.SURVEY_NOT_FOUND);
				}
				answer.getVotes().add(new Vote(answer, currentUser));
				answer.setVotesNumber(answer.getVotesNumber()+1);
			}
			survey.setVoters(survey.getVoters()+1);
		}
	}
}
