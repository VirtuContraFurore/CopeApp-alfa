package com.copeapp.dao.commons;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.copeapp.entities.common.Role;
import com.copeapp.tomcat9Misc.EntityManagerFactoryGlobal;

public class RoleDAO {

	public static List<Role> getRolesList() {
		EntityManager entitymanager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		entitymanager.getTransaction().begin();
		
		TypedQuery<Role> query = entitymanager.createQuery("SELECT r FROM Role r", Role.class);
		List<Role> roles = new ArrayList<Role>(query.getResultList());
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		return roles;
	}

}
