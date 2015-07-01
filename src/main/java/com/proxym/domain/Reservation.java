package com.proxym.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proxym.business.ReservationInfo;
import com.proxym.business.ResourceInfo;

/** Reservation  object is a representation of Reservations data.
 * 
 * @author Nessrine
 * @version 1.0.
 *
 */
//@JsonIgnoreProperties({"id","user","resource"})
@SuppressWarnings("serial")
@Entity
public class Reservation implements Serializable {
	/**
	 * Primary key.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	/**
	 * Date when the reservation starts.
	 */
	private Date dateStart ;
	
	/**
	 * Date when the reservation ends.
	 */
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
	 * List of resources related to this reservation.
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "resource", nullable = false)
	private Resource resource ;

	/**
	 * default constructor.
	 */
	public Reservation() {
		
	}

	/*
	 * Getters and Setters.
	 */
	
	/**
	 * Returns the reservation's ID.
	 *  
	 * @return A <code>Long</code> containing the id of
	 * the current reservation.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the reservation's ID.
	 * 
	 * @param idReservation A <code>Long</code> containing the id of
	 * the current reservation.
	 */
	public void setId(Long idReservation) {
		this.id = idReservation;
	}


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
	 * Gets the user login.
	 * 
	 * @return A <code>String</code> containing the login
	 * of the user.
	 */
	public String getLoginUser() {
		return loginUser;
	}

	/**
	 * Sets the user login.
	 * 
	 * @param loginUser A <code>String</code> containing the login
	 * of the user.
	 */
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	/**
	 * Returns the  resource related to this reservation.
	 * 
	 * @return A Object resource containing the entire
	 *  resource.
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * Sets the list of resources.
	 * 
	 * @param resources  Object resource containing the entire
	 *  resource.
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	/**
	 * get business object from domain one.
	 * @return
	 */
	public ReservationInfo toBusiness (){
		
		ReservationInfo resourceInfo =new ReservationInfo	();
		resourceInfo.setDateEnd(dateEnd);
		resourceInfo.setDateStart(dateStart);
		resourceInfo.setDescription(description);
		resourceInfo.setReferenceResource(this.resource!=null ?this.resource.getReference():null);
		resourceInfo.setLoginUser(loginUser);
		resourceInfo.setId(id);
		return resourceInfo;
}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateEnd == null) ? 0 : dateEnd.hashCode());
		result = prime * result + ((dateStart == null) ? 0 : dateStart.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((loginUser == null) ? 0 : loginUser.hashCode());
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (dateEnd == null) {
			if (other.dateEnd != null)
				return false;
		} else if (!dateEnd.equals(other.dateEnd))
			return false;
		if (dateStart == null) {
			if (other.dateStart != null)
				return false;
		} else if (!dateStart.equals(other.dateStart))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loginUser == null) {
			if (other.loginUser != null)
				return false;
		} else if (!loginUser.equals(other.loginUser))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		return true;
	}
	
	
	
}