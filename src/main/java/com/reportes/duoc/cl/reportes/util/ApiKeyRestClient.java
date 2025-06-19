package com.reportes.duoc.cl.reportes.util;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiKeyRestClient {

    private final RestTemplate restTemplate;

    public ApiKeyRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T get(String url, String apiKey, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<T> response = restTemplate.exchange(
            url, HttpMethod.GET, entity, responseType
        );

        return response.getBody();
    }
}
