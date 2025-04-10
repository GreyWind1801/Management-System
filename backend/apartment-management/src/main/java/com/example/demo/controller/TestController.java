package com.example.demo.controller;  // Use your actual package name

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Marks this class as a REST API controller
@RequestMapping("/test")  // Base URL for this controller
public class TestController {

    @GetMapping("/hello")  // Defines a GET endpoint at /test/hello
    public String sayHello() {
        return "Hello, the API is working!";
    }
}
