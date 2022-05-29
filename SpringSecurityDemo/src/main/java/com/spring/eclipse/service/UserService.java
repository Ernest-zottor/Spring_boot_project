package com.spring.eclipse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.eclipse.model.User;
import com.spring.eclipse.model.UserRepository;

@Service
public class UserService {
	
//	private User user;
	@Autowired
	private UserRepository userRepo;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	
	public User saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
		
	}
	

}
