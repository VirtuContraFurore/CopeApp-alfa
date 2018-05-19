package com.copeapp.dao.commons;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.copeapp.entities.common.Role;
import com.copeapp.utilities.EntityManagerGlobal;

public class RoleDAO {

	public static List<Role> getRolesList() {
	
		TypedQuery<Role> query = EntityManagerGlobal.getEntityManager().createQuery("SELECT r FROM Role r", Role.class);
		List<Role> roles = new ArrayList<Role>(query.getResultList());
		return roles;
	}

}
