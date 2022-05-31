package com.spring.eclipse.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.eclipse.common.UserConstant;
import com.spring.eclipse.entity.User;
import com.spring.eclipse.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public String joinGroup(User user) {
		user.setRoles(UserConstant.DEFAULT_ROLE);
		user.setActive(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return "Hi " + user.getUserName() + " welcome to group !";
	}
	
	
	
	public String giveAccessToUser(int userId, String userRole, Principal principal) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> 
		new UsernameNotFoundException("User with ID "+ userId +" does not exist"));
		
		List<String> rolesThatCanBeAssigned = getRolesThatCanBeAssignedByLoggedinUser(principal);
		if(rolesThatCanBeAssigned.contains(userRole)) {
			String newRole = user.getRoles() + "," +userRole;
			user.setRoles(newRole);
			userRepo.save(user);
			return "Hi "+ user.getUserName() + " "+ userRole +" assign to you by "+ principal.getName();
		}
		
		return principal.getName() +" you cannot assign "+ userRole + " to " + user.getUserName();
		
	}
	
	
	
	private User getLoggedInUser(Principal principal) {
		return userRepo.findByUserName(principal.getName()).get();
		
	}
	
	private List<String> getRolesThatCanBeAssignedByLoggedinUser(Principal principal){
		User loggedInUser = getLoggedInUser(principal);
		List<String> roles = Arrays.asList(loggedInUser.getRoles().split(","));
		if(roles.contains("ROLE_ADMIN")) {
			return Arrays.asList(UserConstant.ADMIN_ACCESS);
		}
		if(roles.contains("ROLE_MODERATOR")) {
			return Arrays.asList(UserConstant.MODERATOR_ACCESS);
		}
		return new ArrayList<>();
	}

}
