package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long>{

}
