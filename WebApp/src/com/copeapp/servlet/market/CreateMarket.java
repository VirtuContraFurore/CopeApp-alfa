package com.copeapp.servlet.market;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.dto.commons.ExceptionDTO;
import com.copeapp.dto.market.CreateMarketRequestDTO;
import com.copeapp.entities.common.User;
import com.copeapp.exceptions.ExType;
import com.copeapp.exceptions.ServerException;
import com.copeapp.utilities.ExDescrForUserUtility;
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
		
		ObjectMapper objMap = new ObjectMapper();
		try {
			CreateMarketRequestDTO createMarketRequest = objMap.readValue(request.getInputStream(), CreateMarketRequestDTO.class);	
			if (!ObjectsValidationUtility.validateNotNullParameters(createMarketRequest)) {
				throw new ServerException(ExType.NOT_NULL_ANNOTATION_NOT_RESPECTED);
			}
			User creator = UserDAO.selectById(createMarketRequest.getCreatorId());
			if (creator == null) {
				throw new ServerException(ExType.WRONG_USER_ID);
			}
			
		} catch (JsonParseException | JsonMappingException ex) {
			ex.printStackTrace();
			ExceptionDTO genSerEx = new ExceptionDTO(ex.getStackTrace(),
																   HttpUtility.httpStatusBadRequest,
																   ServerException.getExceptionDescription(ExType.JSON_FORMAT_ERROR),
																   ExDescrForUserUtility.genericApplicationError);
			response.setStatus(genSerEx.getHttpStatus());
			objMap.writeValue(response.getOutputStream(), genSerEx);
			
		} catch (ServerException gSex) {
			if (gSex.getExceptionType().equals(ExType.NOT_NULL_ANNOTATION_NOT_RESPECTED)) {
				ExceptionDTO genSerEx = new ExceptionDTO(gSex,
																	   HttpUtility.httpStatusBadRequest,
																	   ExDescrForUserUtility.genericApplicationError);
				response.setStatus(genSerEx.getHttpStatus());
				objMap.writeValue(response.getOutputStream(), genSerEx);
			}else if (gSex.getExceptionType().equals(ExType.WRONG_USER_ID)) {
				ExceptionDTO genSerEx = new ExceptionDTO(gSex,
						   HttpUtility.httpStatusBadRequest,
						   ExDescrForUserUtility.genericApplicationError);
				response.setStatus(genSerEx.getHttpStatus());
				objMap.writeValue(response.getOutputStream(), genSerEx);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			ExceptionDTO genSerEx = new ExceptionDTO(ex.getStackTrace(),
																   HttpUtility.httpStatusInternalServerError,
																   ServerException.getExceptionDescription(ExType.GENERIC_SERVER_ERROR),
																   ExDescrForUserUtility.genericApplicationError);
			response.setStatus(genSerEx.getHttpStatus());
			objMap.writeValue(response.getOutputStream(), genSerEx);
		}
	}

}
