package com.copeapp.servlet.market;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.market.CreateMarketRequestDTO;
import com.copeapp.entities.common.User;
import com.copeapp.exceptions.GenericServerException;
import com.copeapp.utilities.HttpUtility;
import com.copeapp.utilities.ObjectsValidationUtility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/createmarket")
public class CreateMarket extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public CreateMarket() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			CreateMarketRequestDTO createMarketRequest = objectMapper.readValue(request.getInputStream(), CreateMarketRequestDTO.class);	
			if (!ObjectsValidationUtility.validateNotNullParameters(createMarketRequest)) {
				throw new GenericServerException();
			}
			User creator = UserDAO.selectById(createMarketRequest.getCreatorId());
			
		} catch (JsonParseException | JsonMappingException | GenericServerException e) {
			e.printStackTrace();
			response.setStatus(HttpUtility.httpStatusBadRequest);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			response.setStatus(HttpUtility.httpStatusInternalServerError);
		}
	}

}
