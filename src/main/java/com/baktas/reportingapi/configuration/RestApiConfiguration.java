package com.baktas.reportingapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestApiConfiguration {

    @Bean
    protected RestTemplate getTemplate() {
        return new RestTemplate();
    }
}