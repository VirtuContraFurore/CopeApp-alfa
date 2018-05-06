package com.copeapp.dao.survey;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.copeapp.entities.survey.Survey;
import com.copeapp.exception.SurveyRequestFailedExcption;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
import com.copeapp.utilities.HttpStatusUtility;

public class SurveyDao {
	
	public static Survey getSurveyById (int surveyId) {
		
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		Query query = entitymanager.createQuery("SELECT s FROM surveys s WHERE (s.surveyId = :surveyId)", Survey.class);
		query.setParameter("surveyId", surveyId);
		Survey survey = (Survey) query.getSingleResult();
		if (survey == null) {
			throw new SurveyRequestFailedExcption(HttpStatusUtility.NOT_FOUND, "Il survey di id "+surveyId+" non è stato trovato");
		}else {
			return survey;
		}
	}

}
