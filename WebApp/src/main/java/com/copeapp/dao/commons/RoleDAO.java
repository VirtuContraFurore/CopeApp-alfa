package com.copeapp.dao.commons;

import java.util.List;

import javax.persistence.TypedQuery;

import com.copeapp.entities.common.Role;
import com.copeapp.tomcat9Misc.EntityManagerGlobal;

public class RoleDAO {

	public static List<Role> getRolesList() {
		
		TypedQuery<Role> query = EntityManagerGlobal.getInstance().getEntityManager().createQuery("SELECT r FROM Role r", Role.class);
		
		return query.getResultList();
	}

}
