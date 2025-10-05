package org.rizki.fintech.service_auth.controller;

import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.dto.LoginRequest;
import org.rizki.fintech.service_auth.dto.LoginResponse;
import org.rizki.fintech.service_auth.dto.RegisterRequest;
import org.rizki.fintech.service_auth.dto.RegisterResponse;
import org.rizki.fintech.service_auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);

        return ResponseEntity.ok(response);
    }
}
