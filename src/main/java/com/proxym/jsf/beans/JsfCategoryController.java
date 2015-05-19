package com.proxym.jsf.beans;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.proxym.business.CategorieInfo;
import com.proxym.domain.Categorie;
import com.proxym.exception.GestionResourceException;
import com.proxym.service.CategorieService;

/**
 * Backed bean for managing the categories
 * views.
 * 
 * @author Nessrine.
 * @version 1.0
 *
 */
@RestController
@Scope("view")
public class JsfCategoryController {

	/**
	 * The logger instance . All log messages from this class are routed through this member.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(JsfCategoryController.class);

	/**
	 * {@link CategorieService}
	 */
	@Autowired
	private CategorieService categorieService;

	/**
	 * object used to hold the category fields.
	 */
	private CategorieInfo categorieInfoToAdd = new CategorieInfo();
	
	/**
	 * the category selected by the user for edit 
	 * or for delete.
	 */
	private Categorie selectedCategory;
	

	/**
	 * add new category.
	 * 
	 * @throws GestionResourceException
	 */
	public void addcategory() throws GestionResourceException {

		LOGGER.debug("add new category", categorieInfoToAdd);
		if (categorieInfoToAdd != null) {
			categorieService.addCategory(categorieInfoToAdd);
			categorieInfoToAdd = new CategorieInfo();
		}
	}
	
	/**
	 * Delete the selected category selected by
	 * the user.
	 * 
	 * @throws GestionResourceException indicates there is a problem.
	 */
	public void deleteCategory() throws GestionResourceException{
		
		LOGGER.debug("delete the category ", selectedCategory);
		if(selectedCategory != null ){
			categorieService.deleteCategory(selectedCategory.getReference());
			selectedCategory=null;
		}
	}
	
	/**
	 * update the category selected b
	 * the user.
	 * 
	 * @throws GestionResourceException indicates there is a problem.
	 */
	public void updateCategory () throws GestionResourceException {
		
		LOGGER.debug("update the category ", selectedCategory);
		if(selectedCategory != null ) {
			categorieService.updateCategory(selectedCategory.getReference(), selectedCategory.toBusiness());
			selectedCategory = null ;
		}
	}

	/**
	 * Gets all the categories.
	 * 
	 * @return A <code>Collection</code> containing all the categories.
	 * 
	 * @throws GestionResourceException indicates there's problem.
	 */
	public List<Categorie> getAllCategories() throws GestionResourceException {
		
		LOGGER.debug("retieving all the categories existing in the system");
		return categorieService.findAll();
	}

	/**
	 * 
	 * @return
	 */
	public CategorieInfo getCategorieInfoToAdd() {
		return categorieInfoToAdd;
	}

	/**
	 * 
	 * @param categorieInfoToAdd
	 */
	public void setCategorieInfoToAdd(CategorieInfo categorieInfoToAdd) {
		this.categorieInfoToAdd = categorieInfoToAdd;
	}

	/**
	 * 
	 * @return
	 */
	public Categorie getSelectedCategory() {
		return selectedCategory;
	}

	/**
	 * 
	 * @param selectedCategory
	 */
	public void setSelectedCategory(Categorie selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
	
	

}
