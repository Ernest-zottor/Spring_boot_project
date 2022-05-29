package com.spring.eclipse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.eclipse.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);
}
