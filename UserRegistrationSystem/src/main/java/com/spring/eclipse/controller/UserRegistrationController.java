package com.spring.eclipse.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.eclipse.model.User;
import com.spring.eclipse.repository.UserRepository;

@RestController
@RequestMapping("api/user")
public class UserRegistrationController {
	private final UserRepository userRepo;

	
	@Autowired
	public UserRegistrationController(UserRepository userRepo) {
		this.userRepo = userRepo;

	}
	
	@GetMapping
	public ResponseEntity<List<User>> listAllUsers() {
	List<User> users = userRepo.findAll();
		
	return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody final User user){
		
			user.setDate(new Date());
			userRepo.save(user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		Optional<User> user = userRepo.findById(id);
		if(!user.isPresent()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(user.get(), HttpStatus.OK);
		}
	
	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newUser){
		Optional<User> user = userRepo.findById(id);
		if(!user.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		User updatedUser = user.get();
		updatedUser.setName(newUser.getName());
		updatedUser.setAddress(newUser.getAddress());
		updatedUser.setEmail(newUser.getEmail());
		
		userRepo.saveAndFlush(updatedUser);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id){
		Optional<User> user = userRepo.findById(id);
		if(!user.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		userRepo.delete(user.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
