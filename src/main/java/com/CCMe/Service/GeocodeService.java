package com.CCMe.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.CCMe.Model.Request.GeocodeResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeocodeService {

    @Value("${GOOGLE_PUBLIC_KEY}")
    private String apiKey;
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public GeocodeResponse getCoordinates(String zipCode) {
        RestTemplate restTemplate = restTemplate();
        String geocodeUrl = "https://maps.googleapis.com/maps/api/geocode/json";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(geocodeUrl)
            .queryParam("address", zipCode)
            .queryParam("key", apiKey);
        
        return restTemplate.getForObject(uriBuilder.toUriString(), GeocodeResponse.class);
    }
}
