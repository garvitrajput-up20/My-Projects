package com.garvitrajput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.garvitrajput.model.Author;
import com.garvitrajput.model.Book;
import com.garvitrajput.services.AuthorService;
import com.garvitrajput.services.BookService;

import java.util.List;
import java.util.Optional;

import javax.management.AttributeNotFoundException;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	@GetMapping("")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable int bookId) {
		Optional<Book> book = bookService.getBookById(bookId);
		return (ResponseEntity<Book>) book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	
	@PostMapping
	public Book addBook(@RequestBody Book book) {
	    try {
	        Author author = authorService.getAuthorById(book.getAuthor().getAuthorid())
	                .orElseThrow(() -> new AttributeNotFoundException("Author not found with id " + book.getAuthor().getAuthorid()));
	        book.setAuthor(author);
	    } catch (AttributeNotFoundException e) {
	        e.printStackTrace();
	    }
	    return bookService.addBook(book);
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updateBook(@PathVariable("id") int id, @RequestBody Book updatedBook) {
	    Optional<Book> optionalBook = bookService.getBookById(id);
	    if (!optionalBook.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    Book book = optionalBook.get();
	    book.setName(updatedBook.getName());
	    book.setAuthor(updatedBook.getAuthor());

	    bookService.addBook(book);
	    return ResponseEntity.ok(book);
	}


	@DeleteMapping("/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable int bookId) {
		bookService.deleteBookById(bookId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
