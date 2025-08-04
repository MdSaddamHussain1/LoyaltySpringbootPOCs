package com.InMemoryAuth.InMemoryAuth.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/hello")
    public String greetUser() {
        return "Authenticated successfully. Welcome!";
    }
}
