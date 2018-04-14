package com.copeapp.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.copeapp.dto.commons.RoleDTO;
import com.copeapp.dto.commons.UserDTO;
import com.copeapp.dto.login.LoginRequestDTO;
import com.copeapp.dto.login.LoginResponseDTO;
import com.copeapp.entities.Role;
import com.copeapp.entities.User;
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
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CopeApp");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin(); //dato che è una select la transaction è inutile
		Query query = entitymanager.createQuery("SELECT u FROM User u WHERE (u.mail = :mail OR u.username = :mail) AND (u.password = :password)", User.class);
		query.setParameter("mail", loginRequest.getMail());
		query.setParameter("password", loginRequest.getPassword());
		try {
			User user = (User) query.getSingleResult();
			ArrayList<RoleDTO> userRoles = new ArrayList<RoleDTO>();
			RoleDTO tmp = new RoleDTO();
			for (Role r : user.getRoles()) {
				BeanUtils.copyProperties(tmp, r);
				userRoles.add(tmp);
			}
			UserDTO ret = new UserDTO(user.getUserId(), user.getMail(), user.getFirstname(), user.getLastname(), user.getUsername(), user.getClasse(), user.getSezione(), user.getPassword(), userRoles, user.getImageUrl(), user.getWallpaper(), user.isFirstEntry());
			loginResponse.setUser(ret);
		} catch (NoResultException nre) {
			response.setStatus(401);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
		
		om.writeValue(response.getOutputStream(), loginResponse);
		
	}

}
