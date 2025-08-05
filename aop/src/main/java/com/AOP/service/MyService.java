package com.AOP.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    public String greet(String name)
    {
        System.out.println("calling greet method");
        return  "Hey ,"+name;
    }
}
