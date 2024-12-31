package com.example.ComplaintSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PageController {
    @GetMapping("home")
    public String renderHomePage() {
        System.out.println("ugtfjgc");
        return "index"; // Renders index.html from the templates folder
    }
}
