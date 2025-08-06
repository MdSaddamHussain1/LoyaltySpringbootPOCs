package com.wiremockPoc.service;

import com.wiremockPoc.model.PetResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PetService {

    private final RestClient restClient;

    public PetService(@Value("${external.api.base-url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public PetResponse getPetById(Long id) {
        return restClient.get()
                .uri("/pet/{id}", id)
                .retrieve()
                .body(PetResponse.class);
    }
}