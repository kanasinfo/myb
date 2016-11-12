package com.myb.portal.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
public class InitListener implements HttpSessionListener,ServletContextListener {
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		arg0.getServletContext().setAttribute("ctx", arg0.getServletContext().getContextPath());
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute("ctx", se.getSession().getServletContext().getContextPath());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

}
