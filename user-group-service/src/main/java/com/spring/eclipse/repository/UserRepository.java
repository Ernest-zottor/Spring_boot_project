package com.spring.eclipse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.eclipse.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	 Optional<User> findByUserName(String UserName);

}
