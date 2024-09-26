package com.moises.becritic.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.moises.becritic.dto.OmdbResponse;

/**
 * Test cases for OmdbService, this will test the methods that returns data from
 * OmdbAPI
 * 
 * @author mplata - 25/09/2024
 */
public class OmdbServiceTest {

    @InjectMocks
    private OmdbService omdbService;

    @Mock
    private RestTemplate restTemplate;

    private AutoCloseable closeable;
    
    @Value("${omdb.api.key}")
    private String apiKey;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        omdbService.setApiKey(apiKey);
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testGetMovieByTitle_Success() {
        String asTitle = "The Matrix";
        String expectedUrl = String.format("http://www.omdbapi.com/?s=%s&apikey=%s", asTitle, apiKey);

        OmdbResponse mockResponse = new OmdbResponse();

        when(restTemplate.getForObject(anyString(), eq(OmdbResponse.class))).thenReturn(mockResponse);

        OmdbResponse response = omdbService.getMovieByTitle(asTitle);

        assertNotNull(response);
        verify(restTemplate, times(1)).getForObject(eq(expectedUrl), eq(OmdbResponse.class));
    }

    @Test
    public void testGetMovieByTitle_NullResponse() {
        String asTitle = "NonExistentMovie";
        String apiKey = "testApiKey";
        String lsUrl = String.format("http://www.omdbapi.com/?s=%s&apikey=%s", asTitle, apiKey);

        when(restTemplate.getForObject(lsUrl, OmdbResponse.class)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            omdbService.getMovieByTitle(asTitle);
        });

        assertEquals("Received null response from OMDB API", exception.getMessage());
    }
}
