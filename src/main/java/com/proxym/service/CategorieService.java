package com.proxym.service;

import java.util.List;

import com.proxym.business.CategorieInfo;
import com.proxym.domain.Categorie;
import com.proxym.exception.GestionResourceException;


/**
 * Contains  the services related to {@link CategorieServiceImpl}.
 * 
 * @author Nessrine
 * @version 1.0
 *
 */
public interface CategorieService {
	
	/**
	 * add new category.
	 * 
	 * @param categorieInfo
	 * @throws GestionResourceException
	 */
	public void addCategory (CategorieInfo categorieInfo) throws GestionResourceException;
	
	/**
	 * update existing category.
	 * 
	 * @param referenceCategory
	 * @param categorieInfo
	 * @throws GestionResourceException
	 */
	public void updateCategory (String referenceCategory,CategorieInfo categorieInfo) throws GestionResourceException;
	
	/**
	 * delete existing categorieInfo.
	 * 
	 * @param referenceCategory
	 * @throws GestionResourceException
	 */
	public void deleteCategory (String referenceCategory) throws GestionResourceException;
	
	/**
	 * Get collection of all the categories existing in the system.
	 * 
	 * @return collection containing all the categories.
	 */
	public List<Categorie> findAll() throws GestionResourceException;
}
