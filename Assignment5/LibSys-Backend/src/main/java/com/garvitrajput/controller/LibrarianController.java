package com.garvitrajput.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garvitrajput.model.Librarian;
import com.garvitrajput.services.LibrarianService;

@RestController
@RequestMapping("/api/librarian")
public class LibrarianController {

	@Autowired
	private LibrarianService librarianService;

	@GetMapping("")
	public ResponseEntity<List<Librarian>> getallLibraian() {
		List<Librarian> librarian = librarianService.getAllLibrarians();
		return new ResponseEntity<>(librarian, HttpStatus.OK);
	}

	@GetMapping("/{librarianId}")
	public ResponseEntity<Librarian> getLibrarianById(@PathVariable int librarianId) {
		Optional<Librarian> librarian = librarianService.getLibrarianById(librarianId);
		return (ResponseEntity<Librarian>) librarian.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/*
	 * @GetMapping("/{username}") public ResponseEntity<Librarian>
	 * getLibrarianByUsername(@PathVariable String username) { Optional<Librarian>
	 * librarian = librarianService.getLibrarianByUsername(username); return
	 * (ResponseEntity<Librarian>) librarian.map(value -> new
	 * ResponseEntity<>(value, HttpStatus.OK)) .orElseGet(() -> new
	 * ResponseEntity<>(HttpStatus.NOT_FOUND)); }
	 */

	@PostMapping
	public ResponseEntity<Librarian> addLibrarian(@RequestBody Librarian librarian) {
		Librarian savedLibrarian = librarianService.addLibrarian(librarian);
		return new ResponseEntity<>(savedLibrarian, HttpStatus.CREATED);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<Librarian> getLibrarianByUsername(@PathVariable String username) {
	    Optional<Librarian> librarian = librarianService.getLibrarianByUsername(username);
	    return (ResponseEntity<Librarian>) librarian.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}



	@DeleteMapping("/{librarianId}")
	public ResponseEntity<Void> deleteLibrraianById(@PathVariable int librarianId) {
		librarianService.deleteLibrarianById(librarianId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}