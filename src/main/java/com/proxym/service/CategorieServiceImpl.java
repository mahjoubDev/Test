package com.proxym.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxym.business.CategorieInfo;
import com.proxym.domain.Categorie;
import com.proxym.exception.GestionResourceException;
import com.proxym.repositories.CategorieRepository;
import com.proxym.utils.ResourceValidator;

/**
 * Service class for managing Categories.
 * 
 * @author Nessrine
 * @version 1.0
 *
 */
@Service("categorieService")
public class CategorieServiceImpl implements CategorieService{
	
	/**
	 * category repository.
	 */
	@Autowired
	private CategorieRepository categorieRepository;
	
	/**
	 * The logger instance . All log messages from this class are routed through
	 * this member.
	 */
	private final  static Logger LOGGER = LoggerFactory.getLogger(CategorieServiceImpl.class);

	/**
	 * {@inheritDoc}.
	 */
	public void addCategory(CategorieInfo categorieInfo)
			throws GestionResourceException {
		
		LOGGER.debug("add new category to data base ", categorieInfo);
		LOGGER.debug("my first example", categorieInfo);
		categorieRepository.save(categorieInfo.toDomain());
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public void updateCategory(String referenceCategory,CategorieInfo categorieInfo)
			throws GestionResourceException {
		
		LOGGER.debug("update existing category into  data base ", referenceCategory);
		Categorie categorieExist=categorieRepository.findByReference(referenceCategory);
		ResourceValidator.checkCategoryExist(referenceCategory, categorieExist);
		Categorie categorieNew=categorieInfo.toDomain();
		categorieNew.setId(categorieExist.getId());
		categorieRepository.save(categorieNew);
		
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public void deleteCategory(String referenceCategory)
			throws GestionResourceException {
		
		LOGGER.debug("delete category fom  data base ", referenceCategory);
		Categorie categorie=categorieRepository.findByReference(referenceCategory);
		ResourceValidator.checkCategoryExist(referenceCategory, categorie);
		categorieRepository.delete(categorie);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Categorie> findAll() throws GestionResourceException {
		
		LOGGER.debug("get alla categories in the system");
		return categorieRepository.findAll();
	}

}
