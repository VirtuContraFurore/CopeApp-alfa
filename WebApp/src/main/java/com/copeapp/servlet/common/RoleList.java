package com.copeapp.servlet.common;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copeapp.dto.commons.RoleListDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/rest/roleList")
public class RoleList extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RoleList() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Content-Type", "application/json");
		ObjectMapper om = new ObjectMapper();
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		entitymanager.getTransaction().begin(); //dato che è una select la transaction è inutile
		String s ="SELECT r FROM Role r";
		TypedQuery<Role> query = entitymanager.createQuery(s, Role.class);
		om.writeValue(response.getOutputStream(), new RoleListDTO(query.getResultList()));
		entitymanager.getTransaction().commit();
		entitymanager.close();
	}
	
}
