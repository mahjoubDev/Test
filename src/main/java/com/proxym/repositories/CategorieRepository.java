package com.proxym.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxym.domain.Categorie;
import com.proxym.exception.GestionResourceException;

/**
 * Spring data repository for the {@link Categorie} entity.
 * 
 * @author Nessrine
 * @version 1.0
 *
 */
public interface CategorieRepository  extends JpaRepository<Categorie, Long>{
	
	/**
	 * get the category object using its reference.
	 * 
	 * @param reference reference used to look for the target object
	 * 
	 * @return Object Categorie or null if the reference doesn't match any one in
	 * the data base.
	 * 
	 * @throws GestionResourceException indicating there is a problem accessing
	 * data base or some what else.
	 *
	 */
	public Categorie findByReference(String reference) throws GestionResourceException;

}
