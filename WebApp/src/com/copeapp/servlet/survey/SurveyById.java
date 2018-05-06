
package com.copeapp.servlet.survey;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mapstruct.factory.Mappers;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.survey.SurveyRequestByIdDTO;
import com.copeapp.dto.survey.SurveyResponseByIdDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Survey;
import com.copeapp.exception.SurveyRequestFailedExcption;
import com.copeapp.mappers.survey.SurveyMapper;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
import com.copeapp.utilities.HttpStatusUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveybyid")
public class SurveyById extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper objMap = new ObjectMapper();
		User currentUser = UserDAO.selectByBasicAuthTokenException(request.getHeader("Authorization"));
		SurveyRequestByIdDTO surveyRequestById = objMap.readValue(request.getInputStream(), SurveyRequestByIdDTO.class);		

		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		Query query = entitymanager.createQuery("SELECT s FROM surveys s WHERE (s.surveyId = :surveyId)", Survey.class);
		query.setParameter("surveyId", surveyRequestById.getSurveyId());
		Survey s = (Survey) query.getSingleResult();

		ArrayList<Role> commonRole = new ArrayList<Role>(currentUser.getRoles());
		commonRole.retainAll(s.getSurveyViewersRoles());
		SurveyResponseByIdDTO responseDTO;
		if (!commonRole.isEmpty()) {
			responseDTO = new SurveyResponseByIdDTO(Mappers.getMapper(SurveyMapper.class).surveyToSurveyDTO(s));
			responseDTO.getSurveyDTO().setVoters(10); //TODO gestione dei votanti
			response.setStatus(HttpStatusUtility.OK);
		} else {
			throw new SurveyRequestFailedExcption(HttpStatusUtility.UNAUTHORIZED, "Non sei autorizzato a vedere questo survey");
		}
		objMap.writeValue(response.getOutputStream(),responseDTO);
	}
}

