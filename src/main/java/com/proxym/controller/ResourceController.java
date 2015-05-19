package com.proxym.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proxym.business.ResourceInfo;
import com.proxym.domain.Resource;
import com.proxym.exception.GestionResourceException;
import com.proxym.service.ResourceService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Rest controller for managing the Resource.
 * 
 * @author Nessrine
 * @version 1.0
 */
@Api(basePath = "/proxym", value = "Resource", description = "Operations with resources", produces = "application/json")
@RestController
@RequestMapping("/proxym/resource")
public class ResourceController extends AbstractRestHandler {

	/**
	 * Resource service {@link ResourceInfo}.
	 */
	@Autowired
	private ResourceService resourceService;

	/**
	 * The logger instance . All log messages from this class are routed through
	 * this member.
	 */
	private final  static Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

	/**
	 * add new resource.
	 * 
	 * @param resource
	 * @throws GestionResourceException
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST,produces = "application/json")
	@ApiOperation(value = "Create new Resource", notes = "Creates new Resource")
	public void addResource (@RequestBody ResourceInfo resourceInfo) throws GestionResourceException{

		LOGGER.debug("add new resource from controller ", resourceInfo);
		resourceService.addResource(resourceInfo);
	}

	/**
	 * update existing resource.
	 * 
	 * @param resourceInfo
	 * @throws GestionResourceException
	 */
	@RequestMapping(value="/{reference:.+}/update",method=RequestMethod.POST,produces = "application/json")
	@ApiOperation(value = "Update existing resource", notes = "Update existing resource")
	public void updateResource (@PathVariable String reference ,@RequestBody ResourceInfo resourceInfo) throws GestionResourceException{

		LOGGER.debug("aupdate existing resource from controller ", reference,resourceInfo);
		resourceService.updateResource(reference,resourceInfo);

	}

	/**
	 * delete existing resource.
	 * 
	 * @param resourceInfo
	 * @throws GestionResourceException
	 */
	@RequestMapping(value="/{reference:.+}/delete",method=RequestMethod.POST,produces = "application/json")
	@ApiOperation(value = "Delete existing resource", notes = "Delete existing resource")
	public void deleteResource (@PathVariable String reference) throws GestionResourceException{

		LOGGER.debug("delete existing resource from controller ", reference);
		resourceService.deleteResource(reference);

	}

	/**
	 * shows all the stored resource.
	 * 
	 * @return A <code>ollection</code> containing all
	 * the resources
	 * 
	 * @throws GestionResourceException indicates there is a problem.
	 */
	@RequestMapping(value="/getList", method=RequestMethod.GET, produces="application/json")
	@ApiOperation(value="get the list of resouces", notes="get the list of resouces")
	public List<ResourceInfo> getAllResource () throws GestionResourceException {

		LOGGER.debug("get all the resources existing in the system");
		return resourceService.findAll();
	}


}
