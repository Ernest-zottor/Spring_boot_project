package com.spring.eclipse.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.eclipse.common.UserConstant;
import com.spring.eclipse.entity.User;
import com.spring.eclipse.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/join")
	public String joinGroup(@RequestBody User user) {
		user.setRoles(UserConstant.DEFAULT_ROLE);
		user.setActive(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return "Hi " + user.getUserName() + " welcome to group !";
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')" )
	public List<User> loadUsers(){
		return userRepo.findAll();
	}
	
	
	@GetMapping("/test")
	@PreAuthorize("hasAuthority('ROLE_USER')" )
	public String testUserAccess() {
		return "user can only access this !";
	}
	
	
	@GetMapping("/access/{userId}/{userRole}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')" )
	public String giveAccessToUser(@PathVariable int userId, @PathVariable String userRole, Principal principal) {
		
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
