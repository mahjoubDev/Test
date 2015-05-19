package com.proxym.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Singleton to handel topaze.proerties file.
 * 
 * @author Denden-OUSSAMA
 * 
 */
public class PropertiesUtil {

	/**
	 * Declaration du log.
	 */
	private static final Log LOGGER = LogFactory.getLog(PropertiesUtil.class);

	/**
	 * Instance unique de la classe.
	 */
	private static PropertiesUtil instance;
	


	/**
	 * private constructor.
	 */
	private PropertiesUtil() {

	}

	/**
	 * @return a single instance of the {@link PropertiesUtil}.
	 */
	public static PropertiesUtil getInstance() {
		if (instance == null)
			instance = new PropertiesUtil();
		return instance;
	}


}
