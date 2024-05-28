package com.garvitrajput.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garvitrajput.model.Book;
import com.garvitrajput.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(int bookId) {
        return bookRepository.findById(bookId);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBookById(int bookId) {
        bookRepository.deleteById(bookId);
    }

	/*
	 * public Book updateBookById(int bookId, Book book) { return bookRepository. }
	 */
}
