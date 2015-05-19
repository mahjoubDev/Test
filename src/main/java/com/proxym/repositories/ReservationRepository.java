package com.proxym.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proxym.business.ReservationInfo;
import com.proxym.domain.Reservation;
import com.proxym.exception.GestionResourceException;

/**
 * Spring data repository for the {@link Reservation} entity.
 * 
 * @author Nessrine
 * @version 1.0
 * 
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	/**
	 * native query for getting the reservations before 15 minute of it's starting date.
	 */
	public static final String GET_RESERVATION_WITH_ONLY_15_MINUTES_REMAINING = "select * from reservation where ((SELECT TIMESTAMPDIFF(MINUTE, date_start, date_end) "
			+"as `difference` FROM reservation)>=15 );";

	/**
	 * gets the reservation that matches the given reference.
	 * 
	 * @param reference
	 *            reference of the reservation.
	 * 
	 * @return An object reservation that matches the reservation otherwise returns a null object if there is not any
	 *         object with the reference.
	 * 
	 * @throws GestionResourceException
	 *             indicates there is a problem.
	 */
	public Reservation findByreference(String reference) throws GestionResourceException;

	/**
	 * get the reservation associated to the target resource and the dateEnd doesn't finish yet.
	 * 
	 * @param reference
	 *            reference of the resource
	 * @return {@link Reservation} or null
	 * @throws GestionResourceExceptionindicates
	 *             there is a problem.
	 */
	@Query(name="findByReferneceResource",value="SELECT r from Reservation r where r.resource.reference LIKE :reference AND  r.dateEnd > now()")
	public Reservation findByReferenceResource(@Param("reference") String reference) throws GestionResourceException;
	
	/**
	 * get the list of reservation befor 15 minutes.
	 * 
	 * @return List of object reservations.
	 * @throws GestionResourceException indicates there is a problem.
	 */
	@Query(nativeQuery=true,value= GET_RESERVATION_WITH_ONLY_15_MINUTES_REMAINING)
	public List<Reservation> getReservationListBeforFiftyMinutes() throws GestionResourceException ;
	
	/**
	 * get the list of reservation by resources.
	 * 
	 * @param refrenceResource
	 * @return
	 * @throws GestionResourceException
	 */
	@Query(name="findByReferneceResource",value="SELECT r from Reservation r where r.resource.reference LIKE :reference")
	public  List<Reservation> findByResource(@Param("reference") String reference) throws GestionResourceException ;

}
