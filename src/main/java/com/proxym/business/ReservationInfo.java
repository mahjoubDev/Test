package com.proxym.business;

import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.proxym.domain.Reservation;
import com.proxym.serialiser.DateDeserializer;
import com.proxym.serialiser.DateSerializer;

public class ReservationInfo {

	/**
	 * Date when the reservation starts.
	 */
	//@JsonSerialize(using=DateSerializer.class)
	@JsonDeserialize(using=DateDeserializer.class)
	private Date dateStart ;

	/**
	 * Date when the reservation ends.
	 */
	//@JsonSerialize(using=DateSerializer.class)
    @JsonDeserialize(using=DateDeserializer.class)
	private Date dateEnd ;

	/**
	 * Description of rservation's purpose.
	 */
	private String description;

	/**
	 * The User who are  doing the reservation.
	 */
	private String loginUser ;

	/**
	 *  resources related to this reservation.
	 */
	private String referenceResource ;
	
	/**
	 * 
	 */
	private Long id ;
	


	/**
	 * default constructor.
	 */
	public ReservationInfo() {

	}

	/**
	 * Constructor using all foelds.
	 * 
	 * @param reference
	 * @param dateStart
	 * @param dateEnd
	 * @param description
	 * @param loginUser
	 * @param referenceResource
	 */
	public ReservationInfo( Date dateStart, Date dateEnd,
			String description, String loginUser, String referenceResource) {
		super();
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.description = description;
		this.loginUser = loginUser;
		this.referenceResource = referenceResource;
	}



	/*
	 * Getters and Setters.
	 */

	/**
	 * Returns the date of starting reservation.
	 * 
	 * @return A <code>Date</code> containing the 
	 * date of starting reservation.
	 */
	public Date getDateStart() {
		return dateStart;
	}

	/**
	 * Sets the date of starting reservation.
	 * 
	 * @param dateStart A <code>Date</code> containing the 
	 * date of starting reservation.
	 */
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * Returns the date of ending reservation.
	 * 
	 * @return  A <code>Date</code> containing the 
	 * date of ending reservation.
	 */
	public Date getDateEnd() {
		return dateEnd;
	}

	/**
	 * Sets the date of ending reservation.
	 * 
	 * @param dateEnd A <code>Date</code> containing the 
	 * date of ending reservation.
	 */
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * Returns the reservation's description.
	 * 
	 * @return A <code>String</code> containing the 
	 * description of reservation.
	 * 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the reservation's description.
	 * 
	 * @param description A <code>String</code> containing the 
	 * description of reservation.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the {@link User} who did the 
	 * reservation.
	 * 
	 * @return A <code>String</code> containing the login of user
	 * who did the reservation.
	 */
	public String getLoginUser() {
		return loginUser;
	}

	/**
	 * Sets the current user who are doing the
	 * this reservation.
	 * 
	 * @param user the User object who are doing  this
	 * reservation
	 */
	public void setLoginUser(String  loginUser) {
		this.loginUser = loginUser;
	}

	/**
	 * Returns the  resource related to this reservation.
	 * 
	 * @return A Object resource containing the entire
	 *  resource.
	 */
	public String getReferenceResource() {
		return referenceResource;
	}

	/**
	 * Sets the list of resources.
	 * 
	 * @param resources  Object resource containing the entire
	 *  resource.
	 */
	public void setReferenceResource(String referenceResource) {
		this.referenceResource = referenceResource;
	}
	
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Create domain reservation object from business one.
	 * 
	 * @return object reservation ready to be persisted 
	 * in the date base.
	 * 
	 */
	public Reservation toDomain(){
		Reservation reservation=new Reservation();
		reservation.setDateEnd(dateEnd);
		reservation.setDateStart(dateStart);
		reservation.setDescription(description);
		reservation.setId(id);
		return reservation;
	}



}
