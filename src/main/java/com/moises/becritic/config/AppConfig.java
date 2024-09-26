package com.moises.becritic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * AppConfig provides with a restTemplate, this RestTemplate follows Singleton pattern
 * this to avoid possible concurrency issues, this method is used to create requests to 
 * OMDB API
 * 
 * @author mplata - 25/09/2024
 */
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
