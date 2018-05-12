package com.copeapp.servlet.login;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.login.LoginRequestDTO;
import com.copeapp.dto.login.LoginResponseDTO;
import com.copeapp.dto.survey.AnswerDTO;
import com.copeapp.entities.common.User;
import com.copeapp.entities.survey.Answer;
import com.copeapp.mappers.commons.GeneralMapper;
import com.copeapp.mappers.commons.UserMapper;
import com.copeapp.utilities.HttpStatusUtility;
import com.copeapp.utilities.ObjectsValidationUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper objMap = new ObjectMapper();
		LoginRequestDTO loginRequest = objMap.readValue(request.getInputStream(), LoginRequestDTO.class);
		ObjectsValidationUtility.validateNonNullParameters(loginRequest);
		User user = UserDAO.selectByUsername(loginRequest.getMail(), loginRequest.getPassword());
		
		if (user.getImageUrl().isEmpty() || user.getImageUrl() == null) { user.setImageUrl(user.getMail()); }
		
		response.setStatus(HttpStatusUtility.OK);
		
		/*
		try {
			AnswerDTO udto = (AnswerDTO)GeneralMapper.convert(new AnswerDTO(1, "ciao", 22), AnswerDTO.class);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		*/
		objMap.writeValue(response.getOutputStream(), new LoginResponseDTO(UserMapper.istance.userToUserDTO(user)));
	}

}
