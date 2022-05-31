package com.spring.eclipse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.eclipse.entity.User;
import com.spring.eclipse.repository.UserRepository;

@Service
public class GroupUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username)
				 .orElseThrow(() ->new UsernameNotFoundException(username +" does not exist"));
		return new GroupUserDetails(user);
	}

}
