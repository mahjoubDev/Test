package com.proxym;

import java.util.HashMap;

import javax.faces.webapp.FacesServlet;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ServletContextAware;

import com.proxym.config.ViewScope;
import com.sun.faces.config.ConfigureListener;

@Configuration
@ComponentScan("com.proxym")
@EnableAutoConfiguration
public class Application  extends SpringBootServletInitializer{

	/**
	 * Entry point fo the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * app config.
	 */
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
         return application.sources(
                new Class[] { Application.class, WebApplicationInitializer.class});
    }

	/**
	 * Register the JSF servlet, is the same as the
	 * declaration in the wed.xml
	 * 
	 * @return
	 */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.jsf");
		return servletRegistrationBean;
	}  
    
//    /**
//     * create a JSF listener.
//     * 
//     * @return {@link ServletListenerRegistrationBean}
//     */
//    @Bean
//	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
//		return new ServletListenerRegistrationBean<ConfigureListener>(
//				new ConfigureListener());
//	}
//	
//    /**
//     * create view scope.
//     * 
//     * @return {@link ViewScope}
//     */
//    @Bean
//	public static ViewScope viewScope() {
//		return new ViewScope();
//	}
//
//	/**
//	 * Allows the use of @Scope("view") on Spring @Component, @Service and @Controller
//	 * beans
//	 */
//	@Bean
//	public static CustomScopeConfigurer scopeConfigurer() {
//		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
//		HashMap<String, Object> hashMap = new HashMap<String, Object>();
//		hashMap.put("view", viewScope());
//		configurer.setScopes(hashMap);
//		return configurer;
//	}
    
    


}
