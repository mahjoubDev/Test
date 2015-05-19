package com.proxym.enums;


/**
 * ENum that definite the type of date ?.
 * <p>
 * 3 type:
 * </p>
 * <ul>
 * <li><b>HOUR</b> : ?.</li>
 * <li><b>DAY</b> : ?.</li>
 * <li><b>WEEK</b> : ?</li>
 * </ul>
 * 
 * @author Nessrine.
 */
public enum TypeDate {

	/**
	 * Indicates that the {@link TypeDate} is  {@link #HOUR}.
	 */
	HOUR,

	/**
	 * Indicates that the {@link TypeDate} is  {@link #DAY}.
	 */
	DAY,

	/**
	 * Indicates that the {@link TypeDate} is  {@link #WEEK}.
	 */
	WEEK;


	/**
	 * This methode will be user par le {@link TypeDateDeserializer} for
	 * making the deserialisation
	 * 
	 * @param type
	 *            type od date.
	 * @return {@link TypeDate}.
	 */
	public static TypeDate fromString(String type) {
		switch (type) {
		case "HOUR":
			return HOUR;
		case "DAY":
			return DAY;
		case "WEEK":
			return WEEK;
		}
		return null;
	}

}
