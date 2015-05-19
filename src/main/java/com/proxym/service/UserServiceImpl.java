package com.proxym.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//import org.exoplatform.container.PortalContainer;
//import org.exoplatform.services.organization.Membership;
//import org.exoplatform.services.organization.OrganizationService;
//import org.exoplatform.services.organization.User;
import org.springframework.stereotype.Service;

import com.proxym.business.UserInfo;

@Service("userService")
public class UserServiceImpl implements UserService {

	public UserInfo getCurrentLoggedUser(String remoteUser) throws Exception {

		UserInfo userInfo = new UserInfo();
//		List<Membership> memberships = null;
//		List<String> roles = new ArrayList<>();
//
//		OrganizationService organizationService = (OrganizationService) PortalContainer
//				.getInstance().getComponentInstanceOfType(
//						OrganizationService.class);
//
//		User user = organizationService.getUserHandler().findUserByName(
//				remoteUser);
//
//		Collection membership = organizationService.getMembershipHandler()
//				.findMembershipsByUser(remoteUser);
//
//		memberships = (List<Membership>) membership;
//		for (Membership membership2 : memberships) {
//			String[] mem = membership2.getGroupId().split("/");
//			roles.add(mem[mem.length - 1]);
//
//		}
//		userInfo.setFirstName(user.getFirstName());
//		userInfo.setLastName(user.getLastName());
//		userInfo.setEmail(user.getEmail());
//		userInfo.setLogin(user.getUserName());
//		userInfo.setRoles(roles);

		return userInfo;
	}
}