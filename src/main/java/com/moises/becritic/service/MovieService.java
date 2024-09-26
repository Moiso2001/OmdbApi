    package com.moises.becritic.service;

    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import com.moises.becritic.exception.ResourceNotFoundException;
    import com.moises.becritic.model.Movie;
    import com.moises.becritic.repository.MoviesRepo;

    
    /**
     * MovieService encapsulate the logic behind the controller request, will handle
     * updates, deletes and select from PostgreSQL
     * 
     * @author mplata - 25/09/2024
     */
    @Service
    public class MovieService {

        @Autowired
        private MoviesRepo gobMoviesRepo;

        /**
         * Creates a SELECT From all the movies on DB
         */
        public List<Movie> getAllMovies() {
            return getGobMoviesRepo().findAll();
        }

        /**
         * Creates an INSERT INTO the movies table on DB with the Movie rated
         * 
         * @param lobMovie
         * @return
         */
        public Movie createMovie(Movie lobMovie) {
            if (getGobMoviesRepo().findByTitle(lobMovie.getTitle()).isPresent()) {
                throw new RuntimeException("Movie already exists with title: " + lobMovie.getTitle());
            }
            
            return getGobMoviesRepo().save(lobMovie);
        }

        /**
         * Allow to UPDATE a movie on DB
         * 
         * @param anId
         * @param aobMovie
         * @return
         */
        public Movie updateMovie(Long anId, Movie aobMovie) {
            Movie lobExistingMovie = getGobMoviesRepo().findById(anId)
                    .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + anId));
            
            lobExistingMovie.setTitle(aobMovie.getTitle());
            lobExistingMovie.setYear(aobMovie.getYear());
            lobExistingMovie.setImdbID(aobMovie.getImdbID());
            lobExistingMovie.setType(aobMovie.getType());
            lobExistingMovie.setPoster(aobMovie.getPoster());
            lobExistingMovie.setRating(aobMovie.getRating());
            lobExistingMovie.setComments(aobMovie.getComments());

            return getGobMoviesRepo().save(lobExistingMovie);
        }

        /**
         * DELETES one Movie on DB by their ID
         * 
         * @param anId
         * @return
         */
        public Boolean deleteMovie(Long anId) {
            if (!getGobMoviesRepo().existsById(anId)) {
                throw new ResourceNotFoundException("Movie not found with id: " + anId);
            }
            getGobMoviesRepo().deleteById(anId);
            return true;
        }

        /* Getters and Setters */
        public MoviesRepo getGobMoviesRepo() {
            return gobMoviesRepo;
        }

        public void setGobMoviesRepo(MoviesRepo gobMoviesRepo) {
            this.gobMoviesRepo = gobMoviesRepo;
        }
    }