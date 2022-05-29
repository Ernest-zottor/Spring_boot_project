package com.spring.eclipse.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.eclipse.model.User;
import com.spring.eclipse.model.UserRepository;
import com.spring.eclipse.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@GetMapping
	public List<User> getUser() {
		return userRepo.findAll();				
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
	@PostMapping("/create")
	public User create(@RequestBody User user) {
		return userService.saveUser(user);
	}

}
