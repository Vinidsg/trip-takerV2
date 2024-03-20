package com.senac.tripTaker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/home")
    public Map<String, Object> index() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bem-vindo à API TripTaker!");
        response.put("status", "Online");
        return response; // Retorna um objeto JSON com informações de boas-vindas
    }
}


