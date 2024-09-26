package com.moises.becritic.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO to represent the response by OmdbAPI
 * 
 * @author mplata - 25/09/2024
 */
public class OmdbResponse {
    @JsonProperty("Search")
    private List<OmdbMovie> search;

    @JsonProperty("totalResults")
    private String totalResults;

    @JsonProperty("Response")
    private String response;

    /* Getters and Setters */
    public List<OmdbMovie> getSearch() {
        return search;
    }

    public void setSearch(List<OmdbMovie> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

