package com.docsehr.patient.controller;

import com.docsehr.patient.security.JwtUtil;
import com.docsehr.patient.model.Admin;
import com.docsehr.patient.model.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    private final Admin hardcodedAdmin = new Admin("admin", "password");

    @PostMapping("/authenticate")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) {
        if (authRequest.getUsername().equals(hardcodedAdmin.getUsername()) &&
                authRequest.getPassword().equals(hardcodedAdmin.getPassword())) {

            String token = jwtUtil.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
