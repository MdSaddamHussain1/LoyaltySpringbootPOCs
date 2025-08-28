package com.example.servicea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ServiceAController {
    private final WebClient webClient;
    @GetMapping("/hello")   // maps root
    public String hello() {
        return "Hello from Service A";
    }

    @Autowired
    public ServiceAController(ReactiveClientRegistrationRepository clients,
                              ReactiveOAuth2AuthorizedClientManager authManager)
    {   //user jwt relay implatemtation fr the client credetial.
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth =
                new ServerOAuth2AuthorizedClientExchangeFilterFunction(authManager);
        oauth.setDefaultClientRegistrationId("service-b");//will use the client credentials flow registered for Service B.
        this.webClient = WebClient.builder()
                .filter(oauth)
                .build();
    }
//here internally calls Service B at http://localhost:8083/api/hello using WebClient for internal caaling
    @GetMapping("/api/call-service-b")
    public String callServiceB() {
        return this.webClient.get()
                .uri("http://localhost:8083/api/hello")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

