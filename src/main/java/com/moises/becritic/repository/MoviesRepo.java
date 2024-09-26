package com.moises.becritic.repository;

import com.moises.becritic.model.Movie;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Repository interface for managing {@link Movie} entities.
 * 
 * This interface extends {@link JpaRepository}, providing standard methods
 * for CRUD operations and additional query capabilities.
 * 
 * @author mplata - 25/09/2024
 */
@RepositoryRestResource
public interface MoviesRepo extends JpaRepository<Movie, Long>{
    Optional<Movie> findByTitle(String title);
}
