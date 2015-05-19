package com.proxym.jsf.beans;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.proxym.controller.ResourceController;
import com.proxym.domain.Categorie;
import com.proxym.domain.Resource;
import com.proxym.exception.GestionResourceException;
import com.proxym.service.ResourceService;

/**
 * Backed bean for managing the resources
 * views.
 * 
 * @author Nessrine
 * @version 1.0
 *
 */
@RestController
@Scope("view")
public class JsfResourceController {
	
	/**
	 * The logger instance . All log messages from this class are routed through this member.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(JsfResourceController.class);
	
	/**
	 * {@link ResourceService}
	 */
	@Autowired
	private ResourceService resourceService;
	
	/**
	 * the category selected by the user 
	 */
	private Categorie selectedCategory;
	
	/**
	 * {@link Resource}
	 */
	private Resource selectedResource ;
	
	/**
	 * {@link Resource}
	 */
	private Resource resourceToAdd = new Resource();
	
	private List<Resource> allResource;
	
//	/**
//	 * Gets all the resources.
//	 * 
//	 * @return A <code>Collection</code> containing all the resources.
//	 * 
//	 * @throws GestionResourceException indicates there's problem.
//	 */
//	public List<Resource> getAllResources () throws GestionResourceException {
//		
//		LOGGER.debug("retieving all the resources existing in the system ");
//		return resourceService.findAll();
//	}
	
	/**
	 * add new resource .
	 * @throws GestionResourceException indicates there is problem.
	 */
	public void addResource () throws GestionResourceException {
		
		LOGGER.debug("add new resource ");
		if(resourceToAdd!=null && resourceToAdd.getCategory()!=null) {
			resourceService.addResource(resourceToAdd.toBusiness());
			resourceToAdd=new Resource();
			
		}
	}
	
	/**
	 * 
	 * @throws GestionResourceException
	 */
	public void deleteResource () throws GestionResourceException {
		LOGGER.debug("add new resource ");
		if(selectedResource!=null){
			resourceService.deleteResource(selectedResource.getReference());
			selectedResource=new Resource();
		}
		
	}
	
	/**
	 * Update existing resource
	 * @throws GestionResourceException
	 */
	public void updateResource () throws GestionResourceException {
		
		LOGGER.debug("update the resource ", selectedResource);
		if(selectedResource != null ) {
			resourceService.updateResource(selectedResource.getReference(), selectedResource.toBusiness());
			selectedResource = null ;
		}
		
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

	/**
	 * 
	 * @return
	 */
	public Resource getResourceToAdd() {
		return resourceToAdd;
	}

	/**
	 * 
	 * @param resourceToAdd
	 */
	public void setResourceToAdd(Resource resourceToAdd) {
		this.resourceToAdd = resourceToAdd;
	}

	/**
	 * 
	 * @return
	 */
	public Resource getSelectedResource() {
		return selectedResource;
	}

	/**
	 * 
	 * @param selectedResource
	 */
	public void setSelectedResource(Resource selectedResource) {
		this.selectedResource = selectedResource;
	}

	
	public List<Resource> getAllResource() throws GestionResourceException {
		LOGGER.debug("retieving all the resources existing in the system ");
		return null; //resourceService.findAll();
	}

	
	public void setAllResource(List<Resource> allResource) {
		this.allResource = allResource;
	}
	
	
	
	
}
