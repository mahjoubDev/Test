package com.proxym.exception;

public class GestionResourceException extends Exception {
	
	/**
	 * Serialization token.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * error code.
	 */
	private final String errorCode;

	/**
	 * Constructor with error code and error message..
	 * 
	 * @param s
	 *            error message.
	 * @param errorCode
	 *            error code.
	 */
	public GestionResourceException(String s, String errorCode) {
		super(s);
		this.errorCode = errorCode;
	}

	/**
	 * constructor with error message and Exception object..
	 * 
	 * @param message
	 *            error message
	 * @param cause
	 *            {@link Throwable}
	 */
	public GestionResourceException(String message, Throwable cause) {
		super(message, cause);
		this.errorCode = null;
	}

	/**
	 * returns the error code
	 * 
	 * @return A <code>String</code> containing the erro code..
	 */
	public String getErrorCode() {
		return errorCode;
	}

}
