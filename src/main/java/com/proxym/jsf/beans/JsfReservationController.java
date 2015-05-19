package com.proxym.jsf.beans;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.proxym.domain.Reservation;
import com.proxym.domain.Resource;
import com.proxym.exception.GestionResourceException;
import com.proxym.service.ReservationService;

/**
 * Backed bean for managing the Reservations 
 * views.
 * 
 * @author Nesrine
 * @version 1.0
 *
 */
@RestController
@Scope("view")
public class JsfReservationController {
	

	/**
	 * The logger instance . All log messages from this class are routed through this member.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(JsfReservationController.class);

	/**
	 * {@link ReservationService}
	 */
	@Autowired
	private ReservationService reservationService;
	
	/**
	 * Object used to hold ne reservation
	 */
	private Reservation reservationToAdd = new Reservation();
	
	/**
	 * rservation selected by user.
	 */
	private Reservation selectedReservation;
	
	/**
	 * 
	 * @return
	 */
	public List<Reservation> getAllReservation () throws GestionResourceException {
		
		LOGGER.debug("get alla existing reservations in the system.");
		return null;//reservationService.findAll();
		
	}
	
	public void addReservation () throws GestionResourceException {
		
		LOGGER.debug("add ne reservation ");
		if(reservationToAdd!=null && reservationToAdd.getResource()!=null) {
			reservationService.addReservation(reservationToAdd.toBusiness());
			reservationToAdd=new Reservation ();
			
		}
	}

	/**
	 * 
	 * @return
	 */
	public Reservation getReservationToAdd() {
		return reservationToAdd;
	}

	/**
	 * 
	 * @param reservationToAdd
	 */
	public void setReservationToAdd(Reservation reservationToAdd) {
		this.reservationToAdd = reservationToAdd;
	}

	/**
	 * 
	 * @return
	 */
	public Reservation getSelectedReservation() {
		return selectedReservation;
	}

	/**
	 * 
	 * @param selectedReservation
	 */
	public void setSelectedReservation(Reservation selectedReservation) {
		this.selectedReservation = selectedReservation;
	}
	
	
	
}
