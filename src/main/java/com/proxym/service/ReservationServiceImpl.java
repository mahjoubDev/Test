package com.proxym.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proxym.business.ReservationInfo;
import com.proxym.domain.Reservation;
import com.proxym.domain.Resource;
import com.proxym.exception.GestionResourceException;
import com.proxym.repositories.ReservationRepository;
import com.proxym.repositories.ResourceRepository;
import com.proxym.utils.ResourceValidator;

/**
 * Service class for managing Reservations.
 * 
 * @author Nessrine
 * @version 1.0
 * 
 */
@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {

	/**
	 * Reservation repository.
	 */
	@Autowired
	private ReservationRepository reservationRepository;

	/**
	 * resource repository.
	 */
	@Autowired
	private ResourceRepository resourceRepository;

	/**
	 * The logger instance . All log messages from this class are routed through
	 * this member.
	 */
	private final static Logger LOGGER = LoggerFactory
			.getLogger(ReservationServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.proxym.service.ReservationService#addReservation(com.proxym.business
	 * .ReservationInfo)
	 */
	public void addReservation(ReservationInfo reservationInfo)
			throws GestionResourceException, ParseException {

		LOGGER.debug("add new reservation to data base ", reservationInfo);

		// check if the coming resource exists in the system otherwise throw
		// exception
		Resource resource = resourceRepository.findByReference(reservationInfo
				.getReferenceResource());
		ResourceValidator.checkResourceExist(
				reservationInfo.getReferenceResource(), resource);

//		Reservation reservationExist = reservationRepository
//				.findOne(reservationInfo.getId());
//		ResourceValidator.checkReservationExistAlready(
//				reservationInfo.getId(), reservationExist);

		ResourceValidator.checkReservationDates(reservationInfo) ;		

		// verify the possibility of the reservation.
		ResourceValidator.checkReservationPossible(resource, reservationInfo);
		
		// check if the resource is available

				List<Reservation> reservations = reservationRepository
						.getReservationForResource(reservationInfo.getDateStart(),
								reservationInfo.getDateEnd(), resource.getId());
				ResourceValidator.checkReservationForResource(reservations,
						reservationInfo.getReferenceResource());
				

		// add the new reservation to data base.
		Reservation reservation = reservationInfo.toDomain();
		reservation.setLoginUser(reservationInfo.getLoginUser());
		reservation.setResource(resource);
		reservation.setId(0l);
		reservationRepository.save(reservation);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.proxym.service.ReservationService#updateReservation(java.lang.String,
	 * com.proxym.business.ReservationInfo)
	 */
	@Transactional
	public void updateReservation(Long id, ReservationInfo reservationInfo)
			throws GestionResourceException, ParseException {

		LOGGER.debug("upadte existing reservation to data base ", reservationInfo);

		// check if the reservation with the target refernece exists in th data base
		Reservation existingReservation = reservationRepository.findOne(id) ;
		ResourceValidator.checkReservationExist(id, existingReservation);


		// check if the coming resource exists in the system otherwise throw exception
		Resource resource = resourceRepository.findByReference(reservationInfo.getReferenceResource());
		ResourceValidator.checkResourceExist(reservationInfo.getReferenceResource(), resource);

		// verify the possibility of the reservation.
		ResourceValidator.checkReservationPossible(resource, reservationInfo);
		
		//check reservation dates
		List<Reservation> reservations =reservationRepository.getReservationForResourceInUpdate(reservationInfo.getDateStart(), 
				reservationInfo.getDateEnd(),resource.getId(),reservationInfo.getId());
		ResourceValidator.checkReservationForResource(reservations, reservationInfo.getReferenceResource());

		
		// update the rservation with the coming informations.
		//Reservation reservation = reservationInfo.toDomain();
		//reservation.setResource(resource);
		///reservation.setId(existingReservation.getId());
		//reservationRepository.delete(existingReservation);
		//reservation.setLoginUser(reservationInfo.getLoginUser());
		existingReservation.setDateEnd(reservationInfo.getDateEnd());
		existingReservation.setDateStart(reservationInfo.getDateStart());
		reservationRepository.save(existingReservation);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.proxym.service.ReservationService#deleteReservation(java.lang.String)
	 */
	public void deleteReservation(Long id)
			throws GestionResourceException {

		LOGGER.debug("delete reservation fom  data base ", id);

		// check if the reservation with the target refernece exists in th data
		// base
		Reservation existingReservation = reservationRepository
				.findOne(id) ;
		ResourceValidator.checkReservationExist(id,
				existingReservation);

		// delete the reservation.
		reservationRepository.delete(existingReservation);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.proxym.service.ReservationService#findAll()
	 */
	@Override
	public List<ReservationInfo> findAll() throws GestionResourceException {

		LOGGER.debug("get all the reservation from the system.");
		List<ReservationInfo> reservationInfos = new ArrayList<ReservationInfo>();
		List<Reservation> reservations = reservationRepository.findAll();
		if (reservations != null && reservations.size() != 0) {
			for (Reservation reservation : reservations) {
				reservationInfos.add(reservation.toBusiness());
			}
		}

		return reservationInfos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.proxym.service.ReservationService#getReservationListBeforFiftyMinutes
	 * ()
	 */
	@Override
	public List<Reservation> getReservationListBeforFiftyMinutes()
			throws GestionResourceException {

		LOGGER.debug("Get the reservations for sending mails");
		return reservationRepository.getReservationListBeforFiftyMinutes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.proxym.service.ReservationService#findAll()
	 */
	@Override
	public List<ReservationInfo> findByResource(String refrenceResource)
			throws GestionResourceException {

		LOGGER.debug("Get the reservations by resource");
		List<ReservationInfo> reservationInfos = new ArrayList<ReservationInfo>();
		List<Reservation> reservations = reservationRepository
				.findByResource(refrenceResource);
		if (reservations != null && reservations.size() != 0) {
			for (Reservation reservation : reservations) {
				reservationInfos.add(reservation.toBusiness());
			}
		}
		return reservationInfos;
	}

}
