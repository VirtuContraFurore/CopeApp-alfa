
package com.copeapp.servlet.survey;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dao.survey.SurveyDAO;
import com.copeapp.dto.survey.SurveyDTO;
import com.copeapp.dto.survey.SurveyRequestByIdDTO;
import com.copeapp.dto.survey.SurveyResponseByIdDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Survey;
import com.copeapp.exception.SurveyExcption;
import com.copeapp.utilities.DozerMapper;
import com.copeapp.utilities.HttpStatusUtility;
import com.copeapp.utilities.MessageUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/surveybyid")
public class SurveyById extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ObjectMapper objMap = new ObjectMapper();
		SurveyRequestByIdDTO surveyRequestById = objMap.readValue(request.getInputStream(), SurveyRequestByIdDTO.class);
		User currentUser = UserDAO.selectByBasicAuthTokenException(request.getHeader("Authorization"));
		Survey requiredSurvey = SurveyDAO.getSurveyById(surveyRequestById.getSurveyId());  //ignorante
		ArrayList<Role> commonRole = new ArrayList<Role>(currentUser.getRoles());
		commonRole.retainAll(requiredSurvey.getSurveyViewersRoles());
		SurveyResponseByIdDTO responseDTO;
		if (!commonRole.isEmpty()) {
			responseDTO = new SurveyResponseByIdDTO(DozerMapper.getMapper().map(requiredSurvey, SurveyDTO.class), false);
			if(SurveyDAO.hasVoted(currentUser, surveyRequestById.getSurveyId())) {
				responseDTO.setHasVoted(true);
			}
			response.setStatus(HttpStatusUtility.OK);
		} else {
			throw new SurveyExcption(HttpStatusUtility.UNAUTHORIZED, MessageUtility.UNAUTHORIZED);
		}
		objMap.writeValue(response.getOutputStream(),responseDTO);
	}
}

