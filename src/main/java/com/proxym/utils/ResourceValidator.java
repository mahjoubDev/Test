package com.proxym.utils;

import java.util.Date;

import com.proxym.business.ReservationInfo;
import com.proxym.domain.Categorie;
import com.proxym.domain.Reservation;
import com.proxym.domain.Resource;
import com.proxym.enums.TypeDate;
import com.proxym.exception.GestionResourceException;

/**
 * Tools class used to validate data coming from the client 
 * part.
 * 
 * @author Nessrine.
 * @version 1.0
 *
 */
public class ResourceValidator {

	/**
	 * check if the category with the target reference
	 * exist in the system.
	 * 
	 * @param referenceCategory reference of the category.
	 * @param categorie the category that matches the reference
	 * @throws GestionResourceException indicates there is a problem.
	 */
	public static void checkCategoryExist(String referenceCategory,Categorie categorie) throws GestionResourceException {
		if(categorie==null){
			throw new GestionResourceException("", "La categorie de reference "+referenceCategory+"  n'existe pas");
		}
	}


	/**
	 * Check if the resource with the target reference
	 * exists in the system.
	 * 
	 * @param reference reference of the resource.
	 * @param resource resource that matches the reference.
	 * @throws GestionResourceException indicates there is a problem.
	 */
	public static void checkResourceExist(String reference,Resource resource) throws GestionResourceException{
		if(resource==null) {
			throw new GestionResourceException("", "La resource de reference "+reference+"  n'existe pas");
		}

	}

	/**
	 * Check if the reservation with the target reference
	 * exist in the system.
	 * 
	 * @param refrence reference of the reservation
	 * @param reservation reservation that matches the reference.
	 * @throws GestionResourceException indicates there is a problem.
	 */
	public static  void checkReservationExist(String reference,Reservation reservation) throws GestionResourceException{
		if(reservation==null){
			throw new GestionResourceException("", "La resoreservationurce de reference "+reference+"  n'existe pas");
		}
	}
	
	public static void checkResourceAvailable(String referenceResource,Reservation reservation) throws GestionResourceException {
		if(reservation != null){
			throw new GestionResourceException("la resource "+referenceResource+" est accupee jusqu'a le "+reservation.getDateEnd(), "1.2");
		}
		
	}

	/**
	 * Check if the duration of reservation is not greater
	 * than the one allowed by the resource.
	 * 
	 * @param resource
	 * @param reservationInfo
	 * @return
	 * @throws GestionResourceException
	 */
	public static void checkReservationPossible(Resource resource, ReservationInfo reservationInfo) throws GestionResourceException{
		if(reservationInfo.getDateStart() == null || reservationInfo.getDateEnd() == null){
			throw new GestionResourceException("le date de debut ou la date de fin est null ", "1.0");
		}
		else {

			Date dateStart=reservationInfo.getDateStart();
			Date dateEnd=reservationInfo.getDateEnd();
			int dureeMax=resource.getDureeMax();

			switch (resource.getTypeDate()) {
			case HOUR: 
				int hours=hoursDifference(dateStart, dateEnd,TypeDate.HOUR);
				if(hours>dureeMax){
					throw new GestionResourceException("La duree de reservation est superieure a la duree mas du resource"
							+" nb: la duree max est :"+dureeMax+" heures", "1.1");
				}
				break;

			case DAY :
				int days=hoursDifference(dateStart, dateEnd,TypeDate.DAY);
				if(days>dureeMax){
					throw new GestionResourceException("La duree de reservation est superieure a la duree mas du resource"
							+" nb: la duree max est :"+dureeMax+" jours", "1.1");
				}
				break;

			case WEEK:

				int weeks=hoursDifference(dateStart, dateEnd,TypeDate.WEEK);
				if(weeks>dureeMax){
					throw new GestionResourceException("La duree de reservation est superieure a la duree mas du resource"
							+" nb: la duree max est :"+dureeMax+" semaines", "1.1");
				}
				break;

			default:

				break;
			}
		}

	}

	/**
	 * Private method to calculate vals between dates
	 * 
	 * @param date1
	 * @param date2
	 * @param typeDate
	 * @return
	 */
	private static  int hoursDifference(Date date1, Date date2,TypeDate typeDate) {

		final int MILLI_TO_HOUR = 1000 * 60 * 60;
		final int MILLI_TO_DAY = MILLI_TO_HOUR*24;
		final int MILLI_TO_WEEK = MILLI_TO_DAY*7;

		int result=0;
		switch (typeDate) {

		case HOUR:
			result= (int) (date2.getTime() - date1.getTime()) / MILLI_TO_HOUR;
			break;

		case DAY:
			result= (int) (date2.getTime() - date1.getTime()) / MILLI_TO_DAY;
			break;

		case WEEK:
			result= (int) (date2.getTime() - date1.getTime()) / MILLI_TO_WEEK;
			break;

		}
		return result;
	}

}
