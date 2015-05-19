package com.proxym.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * Class for configuration of the servlet 
 * Initialization.
 * 
 * @author Nessrine.
 *
 */
@Configuration
public class WebAppInitializer implements ServletContextInitializer {

	/**
	 * set up the config for primefaces.
	 */
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "true");
		servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
	}

}
