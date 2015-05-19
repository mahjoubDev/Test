package com.proxym.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proxym.business.CategorieInfo;
import com.proxym.domain.Categorie;
import com.proxym.exception.GestionResourceException;
import com.proxym.service.CategorieService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Rest controller for managing the Categorie.
 * 
 * @author Nessrine
 * @version 1.0
 *
 */
@Api(basePath = "/proxym", value = "Category", description = "Operations with categories", produces = "application/json")
@RestController
@RequestMapping("/proxym/category")
public class CategorieController extends AbstractRestHandler{
	
	/**
	 * category service {@link CategorieService}.
	 */
	@Autowired
	private CategorieService categorieService;
	
	/**
	 * The logger instance . All log messages from this class are routed through
	 * this member.
	 */
	private final  static Logger LOGGER = LoggerFactory.getLogger(CategorieController.class);
	
	/**
	 * add new category.
	 * 
	 * @param categorie
	 * @throws GestionResourceException
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST,produces = "application/json")
	@ApiOperation(value = "Create new category", notes = "Creates new category")
	
	public void addCategory (@RequestBody CategorieInfo categorieInfo) throws GestionResourceException{
		
		LOGGER.debug("add new category", categorieInfo);
		categorieService.addCategory(categorieInfo);
		
	}
	
	/**
	 * update existing category.
	 * 
	 * @param categorie
	 * @throws GestionResourceException
	 */
	@RequestMapping(value="/{referenceCategory:.+}/update",method=RequestMethod.POST,produces = "application/json")
	@ApiOperation(value = "Update existing categorie", notes = "Update existing categorie")
	public void updateCategory (@PathVariable String referenceCategory, @RequestBody CategorieInfo categorieInfo) throws GestionResourceException{
		
		LOGGER.debug("update existing category", categorieInfo);
		categorieService.updateCategory(referenceCategory,categorieInfo);
	}
	
	/**
	 * delete existing category.
	 * 
	 * @param categorie
	 * @throws GestionResourceException
	 */
	@RequestMapping(value="/{referenceCategory:.+}/delete",method=RequestMethod.POST,produces = "application/json")
	@ApiOperation(value = "Delete existing categorie", notes = "Delete")
	public void deleteCategory (@PathVariable String referenceCategory) throws GestionResourceException{
		
		LOGGER.debug("delete existing category", referenceCategory);
		categorieService.deleteCategory(referenceCategory);
		
	}
	
	/**
	 * get the list of categories in the system.
	 * 
	 * @return Collection of object {@link Categorie}
	 * @throws GestionResourceException indicate there is a problem in processing.
	 * 
	 */
	@RequestMapping(value="/getList",method=RequestMethod.GET,produces="application/json")
	@ApiOperation(value ="get the list of categories in the system", notes="GetListe")
	public List<Categorie> getListCategories() throws GestionResourceException{
		
		LOGGER.debug("get liste of caegories");
		return categorieService.findAll();
		
	}

}
