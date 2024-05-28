package com.garvitrajput.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garvitrajput.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
