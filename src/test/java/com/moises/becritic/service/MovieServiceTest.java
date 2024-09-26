package com.moises.becritic.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.moises.becritic.model.Movie;
import com.moises.becritic.repository.MoviesRepo;
import com.moises.becritic.exception.ResourceNotFoundException;

/**
 * Test cases for MovieServices, it will test different cases of querys to DB
 * 
 * @author mplata - 25/09/2024
 */
public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MoviesRepo moviesRepo;

    private AutoCloseable closeable;  

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testCreateMovie_ShouldThrowExceptionIfMovieExists() {
        Movie mockMovie = new Movie();
        mockMovie.setTitle("The Matrix");
        when(moviesRepo.findByTitle("The Matrix")).thenReturn(Optional.of(mockMovie));

        assertThrows(RuntimeException.class, () -> {
            movieService.createMovie(mockMovie);
        });
    }

    @Test
    public void testGetAllMovies() {
        Movie mockMovie1 = new Movie();
        mockMovie1.setTitle("The Matrix");
        
        Movie mockMovie2 = new Movie();
        mockMovie2.setTitle("Inception");
        
        List<Movie> movies = Arrays.asList(mockMovie1, mockMovie2);
        when(moviesRepo.findAll()).thenReturn(movies);

        List<Movie> returnedMovies = movieService.getAllMovies();
        assertNotNull(returnedMovies);
    }

    @Test
    public void testDeleteMovie_Success() {
        Movie mockMovie = new Movie();
        mockMovie.setId(1L);
        mockMovie.setTitle("Titanic");

        when(moviesRepo.existsById(mockMovie.getId())).thenReturn(true);

        Boolean result = movieService.deleteMovie(mockMovie.getId());

        assertTrue(result);
        verify(moviesRepo, times(1)).deleteById(mockMovie.getId());
    }

    @Test
    public void testDeleteMovie_NotFound() {
        Long nonExistingId = 999L;
        when(moviesRepo.existsById(nonExistingId)).thenReturn(false);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            movieService.deleteMovie(nonExistingId);
        });

        assertEquals("Movie not found with id: 999", exception.getMessage());
    }
}

