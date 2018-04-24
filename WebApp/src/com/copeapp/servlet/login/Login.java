package com.copeapp.servlet.login;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.copeapp.dto.commons.GenericExceptionDTO;
import com.copeapp.dto.commons.RoleDTO;
import com.copeapp.dto.commons.UserDTO;
import com.copeapp.dto.login.LoginRequestDTO;
import com.copeapp.dto.login.LoginResponseDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;
import com.copeapp.tomcat9Misc.StartupOperations;
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
		
		EntityManager entitymanager = StartupOperations.emfactory.createEntityManager();
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
			UserDTO ret = new UserDTO(user.getUserId(), user.getMail(), user.getFirstname(), user.getLastname(), user.getUsername(), user.getClasse(), user.getSezione(), user.getPassword(), userRoles, user.getImageUrl(), user.getWallpaper(), user.getFirstEntry());
			if (ret.getImageUrl().isEmpty() || ret.getImageUrl() == null) {
				ret.setImageUrl(ret.getMail());
			}
			loginResponse.setUser(ret);
			om.writeValue(response.getOutputStream(), loginResponse);
		} catch (NoResultException nre) {
			GenericExceptionDTO errorResponse = new GenericExceptionDTO(nre.getStackTrace(), 401, "Utente non trovato");
			response.setStatus(401);
			om.writeValue(response.getOutputStream(), errorResponse);
		} catch (IllegalAccessException e) {
			GenericExceptionDTO errorResponse = new GenericExceptionDTO(e.getStackTrace(), 500, "Acceso al database negato");
			e.printStackTrace();
			om.writeValue(response.getOutputStream(), errorResponse);
		} catch (InvocationTargetException e) {
			GenericExceptionDTO errorResponse = new GenericExceptionDTO(e.getStackTrace(), 500, "Errore interno al server");
			e.printStackTrace();
			om.writeValue(response.getOutputStream(), errorResponse);
		}
		entitymanager.getTransaction().commit();
		entitymanager.close();
		
	}

}
