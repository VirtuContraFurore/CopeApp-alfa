package com.copeapp.tomcat9Misc;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import com.copeapp.utilities.EntityManagerGlobal;

@WebListener(value = "/rest/*")
public class RequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		if (EntityManagerGlobal.entityManager.get().getTransaction().isActive()) {
			EntityManagerGlobal.entityManager.get().getTransaction().commit();
		}
		EntityManagerGlobal.entityManager.get().close();
		EntityManagerGlobal.entityManager.remove();
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		EntityManagerGlobal.entityManager.set(EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager());
		EntityManagerGlobal.entityManager.get().getTransaction().begin();
	}
	
}