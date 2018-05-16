package com.copeapp.tomcat9Misc;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupOperations implements ServletContextListener {
	
	public StartupOperations() {}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//execute commands after startup
		System.out.println("Server started up");
		//TODO scoprire perch� non va la persistence
		EntityManagerFactoryGlobal.getInstance().createEMFactory();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//execute commands before shutdown
		EntityManagerFactoryGlobal.getInstance().destroyEMFactory();
		System.out.println("Server shut down");
	}
}
