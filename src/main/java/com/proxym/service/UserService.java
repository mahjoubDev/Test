package com.proxym.service;

import java.util.List;

import com.proxym.business.UserInfo;


public interface UserService {
	
	/**
	 * get the current user.
	 * 
	 * @param remoteUser
	 * @return
	 * @throws Exception
	 */
	public UserInfo getCurrentLoggedUser (String remoteUser) throws Exception ;

}
