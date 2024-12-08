package com.organica.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthCheck")
public class HealthCheckController {
    @GetMapping("/")
    public String echo(){
        return "<h1>All is good so far</h2>";
    }
}
