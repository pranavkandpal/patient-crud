package com.docsehr.patient.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/test-secured")
    public ResponseEntity<String> securedTest() {
        return ResponseEntity.ok("JWT Auth passed!");
    }
}
