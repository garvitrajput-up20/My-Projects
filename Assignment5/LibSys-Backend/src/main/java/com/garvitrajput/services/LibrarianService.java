package com.garvitrajput.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garvitrajput.model.Librarian;
import com.garvitrajput.repository.LibrarianRepository;

@Service
@Transactional
public class LibrarianService {

	@Autowired
	private LibrarianRepository librarianRepository;

	public List<Librarian> getAllLibrarians() {
		return librarianRepository.findAll();
	}

	public Optional<Librarian> getLibrarianById(int bookId) {
		return librarianRepository.findById(bookId);
	}

	public Librarian addLibrarian(Librarian librarian) {
		return librarianRepository.save(librarian);
	}
	
	public Optional<Librarian> getLibrarianByUsername(String username) {
	    return librarianRepository.findByUsername(username);
	}


	public void deleteLibrarianById(int LibrarianID) {
		librarianRepository.deleteById(LibrarianID);
	}

}