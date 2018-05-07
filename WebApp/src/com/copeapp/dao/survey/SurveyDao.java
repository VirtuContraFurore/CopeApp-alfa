package com.copeapp.dao.survey;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.copeapp.entities.survey.Survey;
import com.copeapp.exception.SurveyExcption;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
import com.copeapp.utilities.HttpStatusUtility;
import com.copeapp.utilities.MessageUtility;

public class SurveyDao {

	public static Survey getSurveyById (int surveyId) {

		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		Query query = entitymanager.createQuery("FROM surveys s WHERE (s.surveyId = :surveyId)", Survey.class);
		query.setParameter("surveyId", surveyId);
		try {
			return (Survey) query.getSingleResult();
		} catch (NoResultException nre) {
			throw new SurveyExcption(HttpStatusUtility.NOT_FOUND, MessageUtility.INVALID_ID+surveyId, nre);
		}
	}

}
