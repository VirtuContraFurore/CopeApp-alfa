package com.copeapp.tomcat9Misc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter( urlPatterns = {"/*"} )
public class EntityManagerFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Content-Type", "application/json");
		EntityManagerGlobal.getInstance().createEntityManager();
		chain.doFilter(request, response);
		EntityManagerGlobal.getInstance().destroyEntityManager();
	}

	
	
}
