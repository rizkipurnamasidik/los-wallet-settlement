package org.rizki.fintech.service_auth.module.user.controller;

import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.module.user.domain.dto.RegisterRequest;
import org.rizki.fintech.service_auth.module.user.domain.dto.RegisterResponse;
import org.rizki.fintech.service_auth.module.user.domain.dto.UserResponse;
import org.rizki.fintech.service_auth.module.user.application.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = userService.register(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.findAllUser();
    }

}
