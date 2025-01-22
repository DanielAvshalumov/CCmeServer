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
public class GoogleService {

    @Value("${GOOGLE_PUBLIC_KEY}")
    private String apiKey;
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getMiniMap(String zipCode) {
        RestTemplate restTemplate = restTemplate();
        String geocodeUrl = "https://maps.googleapis.com/maps/api/geocode/json";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(geocodeUrl)
            .queryParam("address", zipCode)
            .queryParam("key", apiKey);
        
        GeocodeResponse res = restTemplate.getForObject(uriBuilder.toUriString(), GeocodeResponse.class);
        String latitude = Double.toString(res.getResults().getFirst().getGeometry().getLocation().getLat());
        String longitude = Double.toString(res.getResults().getFirst().getGeometry().getLocation().getLng());
        System.out.println("coordinates "+latitude+" "+longitude);
        String miniMap = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/staticmap")
            .queryParam("markers",latitude+","+longitude)
            .queryParam("size","600x400")
            .queryParam("key",apiKey)
            .queryParam("zoom","14").toUriString();
        System.out.println(miniMap);
        return miniMap;
    }

}
