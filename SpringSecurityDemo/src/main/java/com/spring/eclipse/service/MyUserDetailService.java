package com.spring.eclipse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.eclipse.model.User;
import com.spring.eclipse.model.UserDetailsImp;
import com.spring.eclipse.model.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user =  repo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("404");
		}
		return new UserDetailsImp(user);
	}

}
