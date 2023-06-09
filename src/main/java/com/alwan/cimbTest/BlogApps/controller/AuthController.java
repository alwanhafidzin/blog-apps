package com.alwan.cimbTest.BlogApps.controller;

import com.alwan.cimbTest.BlogApps.dto.LoginRequest;
import com.alwan.cimbTest.BlogApps.dto.RegistrationRequest;
import com.alwan.cimbTest.BlogApps.dto.RegistrationResponse;
import com.alwan.cimbTest.BlogApps.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public RegistrationResponse saveUser(@RequestBody RegistrationRequest request) {
        return authService.register(request);
    }

    @PostMapping("/register/admin")
    public RegistrationResponse saveAdmin(@RequestBody RegistrationRequest request) {
        return authService.registerAdmin(request);
    }
}
