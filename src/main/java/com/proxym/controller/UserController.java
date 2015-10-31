package com.proxym.controller;

import java.util.Arrays;
import java.util.List;

import javax.mail.Part;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proxym.business.UserInfo;
import com.proxym.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Rest controller for managing the Resource.
 * 
 * @author Nessrine
 * @version 1.0
 */
@Api(basePath = "/proxym", value = "User", description = "Operations with Users", produces = "application/json")
@RestController
@RequestMapping("/proxym/user")
public class UserController extends AbstractRestHandler {
	/**
	 * {@link UserService}
	 */
	@Autowired
	private UserService userService;

	/**
	 * The logger instance . All log messages from this class are routed through
	 * this member.
	 */
	private final static Logger LOGGER = LoggerFactory
			.getLogger(ResourceController.class);

	/**
	 * get the current user informations.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/account", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Get current user informations", notes = "Get current user informations")
	public UserInfo getAccount(HttpServletRequest request) throws Exception {

		// return userService.getCurrentLoggedUser(request.getRemoteUser()) ;
		
		

		/*
		 * fake date used only for dev environement and must be removed as soon
		 * as the application is being deployed inthe exo platform
		 */

		List<String> UserRoles = Arrays.asList("users");
		List<String> adminRoles = Arrays.asList("executive-board", "users",
				"administrators", "employees", "web-contributors");
		List<String> superAdminRoles = Arrays.asList("users", "Domain Admins",
				"Proxym-Group", "Enterprise Admins", "Grp_Crm");

		UserInfo simpleUser = new UserInfo("nesrine","nesrine", "rinez",
				"nesrineRines@proxym.it", UserRoles);
		UserInfo admin = new UserInfo("root","root", "root", "root@proxym.it",
				adminRoles);
		UserInfo superAdmin = new UserInfo("admin","admin", "admin", "admin@proxym.it",
				superAdminRoles);

		return admin;

		/* end fake date block */

	}
	
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ApiOperation(value = "Get current user informations", notes = "Get current user informations")
	public UserInfo  testFile ( @RequestPart("file") MultipartFile file , @RequestPart("test") UserInfo test) throws Exception {

		return test;


	}

}
