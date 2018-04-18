package com.copeapp.tomcat9Misc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupOperations implements ServletContextListener {

	public static EntityManagerFactory emfactory;
	
	public StartupOperations() {
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//execute commands before shutdown
		emfactory.close();
		System.out.println("Server shut down");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Server started up");
		emfactory = Persistence.createEntityManagerFactory("CopeApp");
		//execute commands after startup
	}
	
}
