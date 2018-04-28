package com.copeapp.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.commons.UserDTO;
import com.copeapp.dto.login.LoginRequestDTO;
import com.copeapp.dto.login.LoginResponseDTO;
import com.copeapp.entities.common.User;
import com.copeapp.exception.LoginFailedException;
import com.copeapp.utilities.HttpStatusUtility;
import com.copeapp.utilities.ObjectsValidationUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public Login() {  super(); }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Content-Type", "application/json");
		ObjectMapper objMap = new ObjectMapper();
		LoginRequestDTO loginRequest = objMap.readValue(request.getInputStream(), LoginRequestDTO.class);
		ObjectsValidationUtility.validateNotNullParameters(loginRequest);
		
		User user = UserDAO.selectByUsernameException(loginRequest.getMail()); //TODO crea un metodo senza eccezione e if null throw login failed exception
		if (!user.getPassword().equals(loginRequest.getPassword())){
			throw new LoginFailedException(HttpStatusUtility.unauthorized, "Wrong password");
		} else {
			if (user.getImageUrl().isEmpty() || user.getImageUrl() == null) { user.setImageUrl(user.getMail()); }
			response.setStatus(HttpStatusUtility.ok);
			objMap.writeValue(response.getOutputStream(), new LoginResponseDTO(new UserDTO(user)));
		}
	}

}
