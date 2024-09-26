package com.moises.becritic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moises.becritic.dto.OmdbResponse;

/**
 * This service encapsulate the logic behind the OMDB Controller method, this will
 * request a set of movies on OmdbAPI by their titles.
 * 
 * @author mplata - 25/09/2024
 */
@Service
public class OmdbService {
    @Autowired
    private RestTemplate gobRestTemplate;

    @Value("${omdb.api.key}")
    private String API_KEY;

    /**
     * Get a set of movies by their title, it could match multiple movies with the same word
     * 
     * @param asTitle
     * @return
     */
    public OmdbResponse getMovieByTitle(String asTitle) {
        String lsUrl = String.format("http://www.omdbapi.com/?s=%s&apikey=%s", asTitle, API_KEY);
        OmdbResponse lobResponse = getGobRestTemplate().getForObject(lsUrl, OmdbResponse.class);
        
        if (lobResponse == null) {
            throw new RuntimeException("Received null response from OMDB API");
        }
        
        return lobResponse;
    }

    /* Getter and Setters */
    public RestTemplate getGobRestTemplate() {
        return gobRestTemplate;
    }

    public void setGobRestTemplate(RestTemplate gobRestTemplate) {
        this.gobRestTemplate = gobRestTemplate;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }
}
