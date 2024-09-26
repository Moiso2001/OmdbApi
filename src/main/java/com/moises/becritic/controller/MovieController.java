package com.moises.becritic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moises.becritic.dto.ApiResponse;
import com.moises.becritic.dto.OmdbResponse;
import com.moises.becritic.model.Movie;
import com.moises.becritic.service.MovieService;
import com.moises.becritic.service.OmdbService;

/**
 * Main rest controller, here will be the multiple endpoints to process Movies rated
 * saved in PostgreSQL or handle petitions to extern OmdbAPI
 * 
 * @author mplata - 24/09/2024
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService gobMovieService;

    @Autowired
    private OmdbService gobOmdbService;
    
    /**
     * GET Request - Returns all movies with rating in DB
     * 
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Movie>> getAllDbMovies() {
        List<Movie> lobMovies = getGobMovieService().getAllMovies();
        return lobMovies.isEmpty() 
            ? ResponseEntity.notFound().build() 
            : ResponseEntity.ok(lobMovies);
    }

    /**
     * POST Request - Method to save movie with new rating from frontend.
     * 
     * @param aobMovie
     * @return
     */
    @PostMapping
    public ResponseEntity<Movie> saveMovieRated(@RequestBody Movie aobMovie) {
        Movie lobMovieCreated = getGobMovieService().createMovie(aobMovie);
        return (lobMovieCreated == null) 
            ? ResponseEntity.badRequest().build() 
            : ResponseEntity.ok(lobMovieCreated);
    }

    /**
     * DELETE Request - Will delete a movie by their id, if this does not exist
     * it will return proper message
     * 
     * @param anId
     * @return
     */
    @DeleteMapping("/delete/{anId}")
    public ResponseEntity<ApiResponse> deleteMovie(@PathVariable Long anId) {
        boolean lbIsDeleted = getGobMovieService().deleteMovie(anId);
        String message = lbIsDeleted 
            ? "Movie with id: " + anId + " was deleted successfully." 
            : "Movie with id: " + anId + " could not be found.";
        return ResponseEntity.ok(new ApiResponse(message));
    }

    /**
     * GET Request - Method in charge to send petition to OMDB and return a movie
     * by title
     * 
     * @param asTitle
     * @return
     */
    @GetMapping("/{asTitle}")
    public ResponseEntity<OmdbResponse> getMovies(@PathVariable String asTitle) {
        OmdbResponse lobOmdbResponse = getGobOmdbService().getMovieByTitle(asTitle);
        
        if ("True".equals(lobOmdbResponse.getResponse())) {
            return ResponseEntity.ok(lobOmdbResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /* Getter and Setters */
    public MovieService getGobMovieService() {
        return gobMovieService;
    }

    public void setGobMovieService(MovieService gobMovieService) {
        this.gobMovieService = gobMovieService;
    }

    public OmdbService getGobOmdbService() {
        return gobOmdbService;
    }

    public void setGobOmdbService(OmdbService gobOmdbService) {
        this.gobOmdbService = gobOmdbService;
    }

}