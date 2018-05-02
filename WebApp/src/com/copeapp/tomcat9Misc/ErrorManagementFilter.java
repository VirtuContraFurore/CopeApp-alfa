package com.copeapp.tomcat9Misc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dto.commons.ExceptionDTO;
import com.copeapp.exception.CopeAppGenericException;
import com.copeapp.utilities.HttpStatusUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebFilter(
		urlPatterns = {"/*"}
)
public class ErrorManagementFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse res= (HttpServletResponse) response;
		res.setHeader("Content-Type", "application/json");
		
		try {
		chain.doFilter(request, response);
		} catch (CopeAppGenericException e) {
			ObjectMapper om = new ObjectMapper();
			res.setStatus(e.getHttpStatus());
			ExceptionDTO errorResponse = new ExceptionDTO(e, e.getHttpStatus(), e.getMessage());
			om.writeValue(res.getOutputStream(), errorResponse);
		} catch (Throwable e) {
			ObjectMapper om = new ObjectMapper();
			res.setStatus(HttpStatusUtility.INTERNAL_SERVER_ERROR);
			ExceptionDTO errorResponse = new ExceptionDTO(e, HttpStatusUtility.INTERNAL_SERVER_ERROR, "internal server error");
			om.writeValue(res.getOutputStream(), errorResponse);
		}
	}

	
	
}
