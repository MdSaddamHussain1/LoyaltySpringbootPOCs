package com.AOP.controller;


import com.AOP.service.MyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    private final MyService service;

    public MyController(MyService service) {
        this.service = service;
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return service.greet(name);
    }
}
