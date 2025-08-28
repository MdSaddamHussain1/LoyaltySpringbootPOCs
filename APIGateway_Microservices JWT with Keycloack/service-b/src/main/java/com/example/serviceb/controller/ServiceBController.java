package com.example.serviceb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceBController
{
    @GetMapping("/api/hello")   // must match path after StripPrefix
    public String hello() {
        return "Hello from Service B";
    }
}
