package com.proxym.cron;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.proxym.config.MailSender;
import com.proxym.controller.CategorieController;
import com.proxym.domain.Reservation;
import com.proxym.exception.GestionResourceException;
import com.proxym.service.ReservationService;
import com.proxym.utils.Constants;

/**
 * this class is responsible for sending automatically an E-mail for users who have reserved some resources and it
 * remains only 15 minute to start.
 * 
 * @author Nessrine.
 * @version 1.0 .
 * 
 */
@Component
@EnableScheduling
public class CronSendMail {

	/**
	 * The logger instance . All log messages from this class are routed through this member.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(CategorieController.class);

	/**
	 * {@link ReservationService}.
	 */
	@Autowired
	private ReservationService reservationService;

	/**
	 * {@link MailSender}
	 */
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	VelocityEngine velocityEngine;

	/**
	 * Check if there is
	 * 
	 * @throws MessagingException
	 *             indicates there is messaging mail problem.
	 * @throws GestionResourceException
	 *             indicate there is processing problem.
	 */
	//@Scheduled(cron = "0/30 * * * * ?")
	public void sendUserEmail() throws MessagingException, GestionResourceException {
		List<Reservation> reservations = reservationService.getReservationListBeforFiftyMinutes();

		for (Reservation reservation : reservations) {
			
			LOGGER.debug("processing reservation "+reservation.getReference());
			
			String mailUser = reservation.getLoginUser() + Constants.POST_FIX_MAIL;
			SendMail(mailUser, "notification pour la reservation " + reservation.getReference(),
					"Votre reservation pour la " + reservation.getResource().getName() + " bébutera aprés 15 minute ");

		}

		// LOGGER.debug("sending email ");

		// SimpleMailMessage message = new SimpleMailMessage();
		// message.setFrom("noreply@spitter.com");
		// message.setTo("marzouguimahjoub@gmail.com");
		// message.setSubject("New spittle from " + "mahjoub");
		// message.setText("mahjoub" + " says: " + "test sending email from spring boot");
		// mailSender.send(message);

		// MimeMessage message = mailSender.createMimeMessage();
		// MimeMessageHelper helper = new MimeMessageHelper(message, true);
		// helper.setFrom("noreply@spitter.com");
		// helper.setTo("marzouguimahjoub@gmail.com");
		// helper.setSubject("New spittle from " + "mahjoub");
		// helper.setText("<html><body><img src='cid:spitterLogo'>" +
		// "<h4>" + "mahjoub" + " says...</h4>" +
		// "<i>" + "test html text from spring boot" + "</i>" +
		// "</body></html>", true);
		// ClassPathResource couponImage = new ClassPathResource("/images/coupon.jpg");
		// helper.addInline("spitterLogo", couponImage);

		// MimeMessage message = mailSender.createMimeMessage();
		// MimeMessageHelper helper = new MimeMessageHelper(message, true);
		// Map<String, Object> model = new HashMap<String, Object>();
		// model.put("spitterName", "mahjoub");
		// model.put("spittleText", "test from spring boot velocity");
		// String emailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "emailTemplate.vm", model );
		// helper.setText(emailText, true);
		// mailSender.send(message);

		// LOGGER.debug("An email has ben sent successfully");

	}

	/**
	 * private method used to send email for user how have been reserved a resource and it remains only fifty minute for
	 * the starting of reservation;
	 * 
	 * @param mailUser
	 *            the user email address
	 * @param subject
	 *            the subject of the email
	 * @param content
	 *            the content of the email
	 * @throws MessagingException
	 *             indicates there is exception in sending process.
	 */
	private void SendMail(String mailUser, String subject, String content) throws MessagingException {

		LOGGER.debug("begining sending mail to " + mailUser, mailUser, subject, content);

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply@spitter.com");
		message.setTo(mailUser);
		message.setSubject(subject);
		message.setText(content);
		mailSender.send(message);

		LOGGER.debug("mail has been sent successfully to " + mailUser);
	}

}
