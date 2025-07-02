package com.docsehr.patient.controller;

import com.docsehr.patient.model.Admin;
import com.docsehr.patient.model.AuthRequest;
import com.docsehr.patient.repository.AdminRepository;
import com.docsehr.patient.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminRepository adminRepo;

    @PostMapping("/authenticate")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) {
        Optional<Admin> optionalAdmin = adminRepo.findByUsername(authRequest.getUsername());

        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();

            if (admin.getPassword().equals(authRequest.getPassword())) {
                String token = jwtUtil.generateToken(admin.getUsername());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(401).body("Incorrect password");
            }
        } else {
            return ResponseEntity.status(401).body("Admin not found");
        }
    }

    @PostMapping("/admin/signup")
        public ResponseEntity<String> signup(@RequestBody Admin admin) {
            if (adminRepo.findByUsername(admin.getUsername()).isPresent()) {
                return ResponseEntity.badRequest().body("Username already exists");
            }
            adminRepo.save(admin);
            return ResponseEntity.ok("Admin registered successfully");
        }
}
