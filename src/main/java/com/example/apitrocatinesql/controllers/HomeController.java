package com.example.apitrocatinesql.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String main() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
