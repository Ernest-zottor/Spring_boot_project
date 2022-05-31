package com.example.demo.controllers;



import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Book;
import com.example.demo.repositories.BookRepository;

@RequestMapping("/api/book")
@RestController
public class BookController {

	private final BookRepository bookRepository;
	
	@Autowired
	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
		
	}
	
	@GetMapping
	public Set<Book> getBooks() {
		Set<Book> books = new HashSet<>(); 
		 Iterable<Book> iterator = bookRepository.findAll();
		 for(Book book : iterator) {
		books.add(book);
		 }
		 return books;
	}
	
	@PostMapping
	public Book createBook(@RequestBody Book book) {
		
		return bookRepository.save(book);
		
	}
	
	@PutMapping(path = "{id}")
	public Book updateBook(@PathVariable long id, @RequestBody Book book) {
		Optional<Book> oldBook = bookRepository.findById(id);
		if(!oldBook.isPresent()) {
			return null;
		}
		Book oldbook = oldBook.get();
		oldbook.setTitle(book.getTitle());
		oldbook.setIsbn(book.getIsbn());
		oldbook.setAuthors(book.getAuthors());
		oldbook.setPublisher(book.getPublisher());
		return bookRepository.save(oldbook);
		
	}
}
