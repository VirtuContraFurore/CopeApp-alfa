package com.copeapp.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dto.commons.UserDTO;
import com.copeapp.dto.login.LoginRequestDTO;
import com.copeapp.dto.login.LoginResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet("/rest/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Login() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		LoginRequestDTO loginRequest = om.readValue(request.getInputStream(), LoginRequestDTO.class);		
		LoginResponseDTO loginResponse = new LoginResponseDTO();
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("studente");
		roles.add("moderatore");
		roles.add("admin");
		UserDTO user = new UserDTO(0, "cerammerda@gioli.it", "Luca",
				"Ceragioli", "Cerammerda", "5", "F", "VincioGay", 
				roles, "", "default", false);
		loginResponse.setUser(user);
		om.writeValue(response.getOutputStream(), loginResponse);
		
	}

}
