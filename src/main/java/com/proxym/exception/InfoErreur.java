package com.proxym.exception;

/**
 * Message customize for the controllers methods that causes errors.
 * 
 * @author Nessrine
 * @version 1.0
 * 
 */
public class InfoErreur {

	/**
	 * url of  service REST.
	 */
	private String url;

	/**
	 * error code.
	 */
	private String errorCode;

	/**
	 * error message.
	 */
	private String errorMessage;

	/**
	 * default constructor.
	 */
	public InfoErreur() {

	}

	/**
	 * constructor with params..
	 * 
	 * @param url
	 *            url of service REST.
	 * @param errorCode
	 *            error code.
	 * @param errorMessage
	 *            error message.
	 */
	public InfoErreur(String url, String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.url = url;
		this.errorMessage = errorMessage;
	}

	/**
	 * @return {@link #url}.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            {@link #url}.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return {@link #errorCode}.
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            {@link #errorCode}.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return {@link #errorMessage}.
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            {@link #errorMessage}.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
