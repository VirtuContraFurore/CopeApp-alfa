
package com.copeapp.servlet.survey;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mapstruct.factory.Mappers;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dao.survey.SurveyDao;
import com.copeapp.dto.survey.SurveyRequestByIdDTO;
import com.copeapp.dto.survey.SurveyResponseByIdDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Survey;
import com.copeapp.exception.SurveyRequestFailedExcption;
import com.copeapp.mappers.survey.SurveyMapper;
import com.copeapp.utilities.HttpStatusUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveybyid")
public class SurveyById extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper objMap = new ObjectMapper();
		SurveyRequestByIdDTO surveyRequestById = objMap.readValue(request.getInputStream(), SurveyRequestByIdDTO.class);
		User currentUser = UserDAO.selectByBasicAuthTokenException(request.getHeader("Authorization"));

		Survey requiredSurvey = SurveyDao.getSurveyById(surveyRequestById.getSurveyId());

		ArrayList<Role> commonRole = new ArrayList<Role>(currentUser.getRoles());
		commonRole.retainAll(requiredSurvey.getSurveyViewersRoles());
		SurveyResponseByIdDTO responseDTO;
		if (!commonRole.isEmpty()) {
			responseDTO = new SurveyResponseByIdDTO(Mappers.getMapper(SurveyMapper.class).surveyToSurveyDTO(requiredSurvey));
			responseDTO.getSurveyDTO().setVoters(10); //TODO gestione dei votanti
			response.setStatus(HttpStatusUtility.OK);
		} else {
			throw new SurveyRequestFailedExcption(HttpStatusUtility.UNAUTHORIZED, "Non sei autorizzato a vedere questo survey");
		}
		objMap.writeValue(response.getOutputStream(),responseDTO);
	}
}

