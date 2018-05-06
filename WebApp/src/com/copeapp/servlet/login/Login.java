package com.copeapp.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mapstruct.factory.Mappers;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.login.LoginRequestDTO;
import com.copeapp.dto.login.LoginResponseDTO;
import com.copeapp.entities.common.User;
import com.copeapp.exception.LoginFailedException;
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
		ObjectsValidationUtility.validateNotNullParameters(loginRequest);
		User user = UserDAO.selectByUsername(loginRequest.getMail());
		if (!user.getPassword().equals(loginRequest.getPassword())){
			throw new LoginFailedException(HttpStatusUtility.UNAUTHORIZED, "Wrong password");
		} else {
			/* TODO: questo deve essere fatto a lato client
			 * if (user.getImageUrl().isEmpty() || user.getImageUrl() == null) { user.setImageUrl(user.getMail()); }
			 */
			response.setStatus(HttpStatusUtility.OK);
			objMap.writeValue(response.getOutputStream(), new LoginResponseDTO(Mappers.getMapper(UserMapper.class).userToUserDTO(user)));
		}
	}

}
