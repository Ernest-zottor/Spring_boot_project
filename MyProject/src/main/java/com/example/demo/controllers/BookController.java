package com.example.demo.controllers;



import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Book;
import com.example.demo.repositories.BookRepository;

@RequestMapping("/")
@RestController
public class BookController {

	private final BookRepository bookRepository;
	
	@Autowired
	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
		
	}
	
	@GetMapping("/books")
	public Set<Book> getBooks() {
		Set<Book> books = new HashSet<>(); 
		 Iterable<Book> iterator = bookRepository.findAll();
		 for(Book book : iterator) {
		books.add(book);
		 }
		 return books;
	}
}
