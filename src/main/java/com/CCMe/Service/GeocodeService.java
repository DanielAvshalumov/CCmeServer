package com.CCMe.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.CCMe.Model.Request.GeocodeResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeocodeService {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public GeocodeResponse getCoordinates(String zipCode) {
        RestTemplate restTemplate = restTemplate();
        String apiKey = "AIzaSyDn59NgA0kr5b-LvHOL7UFAaCa1yYp0MSM";
        String geocodeUrl = "https://maps.googleapis.com/maps/api/geocode/json";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(geocodeUrl)
            .queryParam("address", zipCode)
            .queryParam("key", apiKey);
        
        return restTemplate.getForObject(uriBuilder.toUriString(), GeocodeResponse.class);
    }
}
