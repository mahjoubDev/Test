package com.proxym.utils;

import java.text.SimpleDateFormat;

/**
 * Class that holds all the constants in tha system.
 * 
 * @author Nessrine.
 * @version 1.0.
 *
 */
public class Constants {
	
	/**
	 * default constructor, it have been made private for
	 * prevent any one else from  instantiate it, all it's
	 * members have a static accessed  the can be access from any
	 * class in the system without need for any instantiation.
	 */
	private Constants() {
		
	}
	
	/**
	 * Default date format : yyyy-MM-dd HH:mm:ss.
	 */
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Default date format witout time : yyyy-MM-dd.
	 */
	public static final SimpleDateFormat DEFAULT_DATE_WITHOUT_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * set today's date to date system.
	 */
	public static final String NOW = "[now]";
	
	/**
	 * Number of minute remains for notifying the user 
	 * that it's reservation is ready.
	 */
	public static final Integer MAX_REMAINING=15; 
	
	/**
	 * the default code if an internal exception have been left.
	 */
	public static final String DEFAULT_CODE_ERROR = "0.3";
	
	/**
	 * the code error code for syntactically incorrect Requette.
	 */
	public static final String CODE_ERROR_SYNTAXE = "0.4";
	
	/**
	 * postFix string mail that will be concatenate to
	 * login user to form the mail address.
	 */
	public static String POST_FIX_MAIL="@proxym.com";

}
