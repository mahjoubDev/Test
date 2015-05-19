package com.proxym.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.proxym.exception.GestionResourceException;
import com.proxym.exception.InfoErreur;
import com.proxym.utils.Constants;

/**
 * this Class is responsible for handling exceptions.
 * 
 * @author Nessrine.
 * @version 1.0.
 *
 */
@Configurable
//@PropertySource("classpath:exception.properties")
public abstract class AbstractRestHandler {

	/**
	 * The logger instance . All log messages from this class are routed through
	 * this member.
	 */
	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment environment;

	/**
	 * 
	 * manage the cases when we have {@link GestionResourceException}
	 * 
	 * @param req
	 *            request HttpServletRequest.
	 * @param ex
	 *            exception
	 * @return {@link InfoErreur}
	 */
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ GestionResourceException.class })
	@ResponseBody
	InfoErreur handleOpaleException(HttpServletRequest req, Exception ex) {
		return new InfoErreur(req.getRequestURI(), ((GestionResourceException) ex).getErrorCode(), ex.getLocalizedMessage());
	}

	/**
	 * 
	 * manage the cases when we have {@link Exception}
	 * 
	 * @param req
	 *            request HttpServletRequest.
	 * @param ex
	 *            exception
	 * @return {@link InfoErreur}
	 */
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class })
	@ResponseBody
	InfoErreur handleException(HttpServletRequest req, Exception ex) {
		LOGGER.error("interne error internale  500", ex);
		return new InfoErreur(req.getRequestURI(), Constants.DEFAULT_CODE_ERROR,ex.getLocalizedMessage());
	}

	/**
	 * 
	 * manage the cases when we have {@link HttpMessageNotReadableException}
	 * 
	 * @param req
	 *            request HttpServletRequest.
	 * @param ex
	 *            exception
	 * @return {@link InfoErreur}
	 */
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	@ResponseBody
	InfoErreur handleMessageNotReadableException(HttpServletRequest req, HttpMessageNotReadableException ex) {
		return new InfoErreur(req.getRequestURI(), Constants.CODE_ERROR_SYNTAXE,"code syntaxiquement incorrect");
	}

}
