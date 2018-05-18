package com.copeapp.utilities;

import javax.persistence.EntityManager;

public class EntityManagerGlobal {

	public static ThreadLocal<EntityManager> entityManager;
	
}
