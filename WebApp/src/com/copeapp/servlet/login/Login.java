package com.copeapp.servlet.login;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.commons.ExceptionDTO;
import com.copeapp.dto.commons.UserDTO;
import com.copeapp.dto.login.LoginRequestDTO;
import com.copeapp.dto.login.LoginResponseDTO;
import com.copeapp.entities.common.User;
import com.copeapp.utilities.HttpStatusUtility;
import com.copeapp.utilities.ObjectsValidationUtility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public Login() {  super(); }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper objMap = new ObjectMapper();
		try {
			LoginRequestDTO loginRequest = objMap.readValue(request.getInputStream(), LoginRequestDTO.class);
			if (!ObjectsValidationUtility.validateNotNullParameters(loginRequest)) {
				response.setStatus(HttpStatusUtility.unauthorized);
				ExceptionDTO errorResponse = new ExceptionDTO(null, HttpStatusUtility.unauthorized, "Errore interno all'applicazione", "La richiesta è ben formatta ma presenta alcuni attributi nulli (che non devono esserlo)");
				objMap.writeValue(response.getOutputStream(), errorResponse);
			}else {
				User user = UserDAO.selectByUsernameException(loginRequest.getMail());
				if (!user.getPassword().equals(loginRequest.getPassword())){
					response.setStatus(HttpStatusUtility.unauthorized);
					ExceptionDTO errorResponse = new ExceptionDTO(null, HttpStatusUtility.unauthorized, "Password errata");
					objMap.writeValue(response.getOutputStream(), errorResponse);
				}else {
					if (user.getImageUrl().isEmpty() || user.getImageUrl() == null) { user.setImageUrl(user.getMail()); }
					response.setStatus(HttpStatusUtility.ok);
					objMap.writeValue(response.getOutputStream(), new LoginResponseDTO(new UserDTO(user)));
				}
			}
		} catch (JsonParseException | JsonMappingException ex) {
			ExceptionDTO errorResponse = new ExceptionDTO(ex.getStackTrace(), HttpStatusUtility.badRequest, "Errore interno all'applicazione", "La richiesta mandata è formattata male");
			response.setStatus(HttpStatusUtility.badRequest);
			objMap.writeValue(response.getOutputStream(), errorResponse);
			
		} catch (NoResultException nre) {
			ExceptionDTO errorResponse = new ExceptionDTO(nre.getStackTrace(), HttpStatusUtility.unauthorized, "Utente errato");
			response.setStatus(HttpStatusUtility.unauthorized);
			objMap.writeValue(response.getOutputStream(), errorResponse);
			
		} catch (Exception ex) {
			ExceptionDTO errorResponse = new ExceptionDTO(ex.getStackTrace(), HttpStatusUtility.internalServerError, "Errore interno all'applicazione", "Avvenuto errore non previsto");
			response.setStatus(HttpStatusUtility.internalServerError);
			objMap.writeValue(response.getOutputStream(), errorResponse);
			
		}
	}

}
