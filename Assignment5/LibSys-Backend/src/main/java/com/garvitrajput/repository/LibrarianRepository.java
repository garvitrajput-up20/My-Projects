package com.garvitrajput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.garvitrajput.model.Librarian;
import java.util.Optional;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
    Optional<Librarian> findByUsername(String username);
}
