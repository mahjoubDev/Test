package com.proxym.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proxym.business.ReservationInfo;
import com.proxym.domain.Reservation;
import com.proxym.exception.GestionResourceException;
import com.proxym.service.ReservationService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Rest controller for managing the Reservation.
 * 
 * @author Nessrine
 * @version 1.0
 */
@Api(basePath = "/proxym", value = "Reservation", description = "Operations with booking", produces = "application/json")
@RestController
@RequestMapping("/proxym/reservation")
public class ReservationController extends AbstractRestHandler {
	
	/**
	 * reservation service {@link ReservationService}.
	 */
	@Autowired
	private ReservationService reservationService;
	
	/**
	 * The logger instance . All log messages from this class are routed through
	 * this member.
	 */
	private final  static Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
	/**
	 * add new reservation.
	 * 
	 * @param reservationInfo
	 * @throws GestionResourceException
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST,produces = "application/json")
	@ApiOperation(value = "Create new booking", notes = "Create new booking booking")
	public void addReservation (@RequestBody ReservationInfo reservationInfo) throws GestionResourceException{
		
		LOGGER.debug("adding new reservation from controller ", reservationInfo);
		reservationService.addReservation(reservationInfo);

	}
	
	/**
	 * update existing reservation.
	 * 
	 * @param reservationInfo
	 * @throws GestionResourceException
	 */
	@RequestMapping(value="/{referenceReservation:.+}/update",method=RequestMethod.POST,produces = "application/json")
	@ApiOperation(value = "Update existing booking", notes = "Update existing booking")
	public void updateReservation (@PathVariable String referenceReservation, @RequestBody ReservationInfo reservationInfo) throws GestionResourceException{
		
		LOGGER.debug("updating  existing  reservation from controller ", referenceReservation,reservationInfo);
		reservationService.updateReservation(referenceReservation,reservationInfo);
		
	}
	
	/**
	 * shows all the existing reservation in that have been added to
	 * the system.
	 * 
	 * @return A <code>Collection</code> containing all the reservations.
	 * @throws GestionResourceException indicates there is a problem.
	 */
	@RequestMapping(value="/getList", method=RequestMethod.GET,produces="application/json")
	@ApiOperation(value="get the list of reservation", notes="get the list of reservation")
	public List<ReservationInfo> getAllReservation() throws GestionResourceException {
		
		LOGGER.debug("get all the reservation");
		List<ReservationInfo> reservationInfos = reservationService.findAll();
		return reservationInfos;
		
	}
	
	/**
	 * shows all the existing reservation in that have been added to
	 * the system.
	 * 
	 * @return A <code>Collection</code> containing all the reservations.
	 * @throws GestionResourceException indicates there is a problem.
	 */
	@RequestMapping(value="/{referenceResource:.+}/getList", method=RequestMethod.GET,produces="application/json")
	@ApiOperation(value="get the list of reservation related to target resource", notes="get the list of reservation related to target resource")
	public List<ReservationInfo> getReservationByResource(@PathVariable String referenceResource) throws GestionResourceException {
		
		LOGGER.debug("get all the reservation");
		List<ReservationInfo> reservationInfos = reservationService.findByResource(referenceResource);
		return reservationInfos;
		
	}
	
	/**
	 * delete existing reservation.
	 * 
	 * @param reservationInfo
	 * @throws GestionResourceException
	 */
	@RequestMapping(value="/{referenceReservation:.+}/delete",method=RequestMethod.POST,produces = "application/json")
	@ApiOperation(value = "Delete existing booking", notes = "Delete existing booking")
	public void deleteReservation (@PathVariable String referenceReservation) throws GestionResourceException{
		
		LOGGER.debug("deleting existing reservation from controller ", referenceReservation);
		reservationService.deleteReservation(referenceReservation);
		
	}
	

}
