package com.proxym.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxym.business.ResourceInfo;
import com.proxym.domain.Categorie;
import com.proxym.domain.Resource;
import com.proxym.exception.GestionResourceException;
import com.proxym.repositories.CategorieRepository;
import com.proxym.repositories.ResourceRepository;
import com.proxym.utils.ResourceValidator;

/**
 * Service class for managing Resources.
 * 
 * @author Nessrine
 * @version 1.0
 *
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	/**
	 * Resource repository.
	 */
	@Autowired
	private ResourceRepository resourceRepository;

	/**
	 * categiry repository.
	 * 
	 */
	@Autowired
	private CategorieRepository categorieRepository;

	/**
	 * The logger instance . All log messages from this class are routed through
	 * this member.
	 */
	private final  static Logger LOGGER = LoggerFactory.getLogger(ResourceServiceImpl.class);

	/**
	 * {@inheritDoc}.
	 */
	public void addResource(ResourceInfo resourceInfo)
			throws GestionResourceException {

		LOGGER.debug("add new resource to data base ", resourceInfo);
		Categorie ExistingCategory=categorieRepository.findByReference(resourceInfo.getReferenceCategory());
		ResourceValidator.checkCategoryExist(resourceInfo.getReferenceCategory(), ExistingCategory);
		
		Resource resourceExist = resourceRepository.findByReference(resourceInfo.getReference()) ;
		ResourceValidator.checkResourceExistAlready(resourceInfo.getReference(), resourceExist);
		
		Resource resource=resourceInfo.toDomain();
		resource.setCategory(ExistingCategory);
		resourceRepository.save(resource);

	}

	/**
	 * {@inheritDoc}.
	 */
	public void updateResource(String reference, ResourceInfo resourceInfo)
			throws GestionResourceException {

		LOGGER.debug("update existing resource into  data base ", resourceInfo);

		//check if the resource exists otherwise thro an exception
		Resource existingResource=resourceRepository.findByReference(reference);
		ResourceValidator.checkResourceExist(reference, existingResource);

		//check if the categori exists otherwise throw nan exception
		Categorie ExistingCategory=categorieRepository.findByReference(resourceInfo.getReferenceCategory());
		ResourceValidator.checkCategoryExist(resourceInfo.getReferenceCategory(), ExistingCategory);

		Resource resource=resourceInfo.toDomain();
		resource.setId(existingResource.getId());
		resource.setCategory(ExistingCategory);
		resourceRepository.save(resource);

	}

	/**
	 * {@inheritDoc}.
	 */
	public void deleteResource(String reference)
			throws GestionResourceException {

		LOGGER.debug("delete resource fom  data base ", reference);

		//check if the resource exists otherwise thro an exception
		Resource existingResource=resourceRepository.findByReference(reference);
		ResourceValidator.checkResourceExist(reference, existingResource);
		resourceRepository.delete(existingResource);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ResourceInfo> findAll() throws GestionResourceException {

		LOGGER.debug("get all the resources ewisting in the data base");
		List<ResourceInfo> resourceInfos =new ArrayList<>() ;
		List<Resource> resources = resourceRepository.findAll();
		if(resources != null && resources.size() != 0 ){
			for(Resource resource:resources){
				resourceInfos.add(resource.toBusiness());
			}
		}
		return resourceInfos;
	}


}
