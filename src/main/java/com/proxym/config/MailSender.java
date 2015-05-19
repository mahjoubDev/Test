package com.proxym.config;

import java.util.Properties;

import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

/**
 * This class sets up all the configuration needed
 * by the system to send an E-mail.
 * 
 * @author TECNETMEDIA
 * @version 1.0
 *
 */
@Configuration
@PropertySource("classpath:mails/mail.properties")
public class MailSender {

	/**
	 * the protocol.
	 */
	@Value("${mail.protocol}")
	private String protocol;

	/**
	 * the host.
	 */
	@Value("${mail.host}")
	private String host;

	/**
	 * the email port.
	 */
	@Value("${mail.port}")
	private int port;

	/**
	 * authentification mail.
	 */
	@Value("${mail.smtp.auth}")
	private boolean auth;

	/**
	 * using the tls protocol.
	 */
	@Value("${mail.smtp.starttls.enable}")
	private boolean starttls;

	/**
	 * mail sender account.
	 */
	@Value("${mail.from}")
	private String from;

	/**
	 * mail sender's user name.
	 */
	@Value("${mail.username}")
	private String username;

	/**
	 * mail sender's password.
	 */
	@Value("${mail.password}")
	private String password;

	/**
	 * Create the {@link JavaMailSender} class that will
	 * be used to prepare and send mails.

	 * @return A object representing the {@link JavaMailSender}
	 */
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.smtp.starttls.enable", "true");
		mailSender.setJavaMailProperties(mailProperties);
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setProtocol(protocol);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		return mailSender;
	}
//
//	@Bean
//	public VelocityEngineFactoryBean velocityEngine() {
//		VelocityEngineFactoryBean velocityEngine =
//				new VelocityEngineFactoryBean();
//		Properties props = new Properties();
//		props.setProperty("resource.loader", "class");
//		props.setProperty("class.resource.loader.class",ClasspathResourceLoader.class.getName());
//		velocityEngine.setVelocityProperties(props);
//		return velocityEngine;
//	}
}