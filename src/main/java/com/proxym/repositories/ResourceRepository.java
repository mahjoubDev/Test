package com.proxym.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxym.domain.Categorie;
import com.proxym.domain.Resource;
import com.proxym.exception.GestionResourceException;

/**
 * Spring data repository for the {@link Resource} entity.
 * 
 * @author Nessrine
 * @version 1.0
 *
 */
public interface ResourceRepository  extends JpaRepository<Resource, Long>{
	
	/**
	 * get the resource object using its reference.
	 * 
	 * @param  reference used to look for the target object
	 * 
	 * @return Object Resource or null if the reference doesn't match any one in
	 * the data base.
	 * 
	 * @throws GestionResourceException indicating there is a problem accessing
	 * data base or some what else.
	 *
	 */
	public Resource findByReference(String reference) throws GestionResourceException;

}
