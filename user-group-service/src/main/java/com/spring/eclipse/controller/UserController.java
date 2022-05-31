package com.spring.eclipse.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.eclipse.entity.User;
import com.spring.eclipse.repository.UserRepository;
import com.spring.eclipse.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/join")
	public String joinGroup(@RequestBody User user) {
		return userService.joinGroup(user);
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
		return userService.giveAccessToUser(userId, userRole, principal);
	
	}
	
}
