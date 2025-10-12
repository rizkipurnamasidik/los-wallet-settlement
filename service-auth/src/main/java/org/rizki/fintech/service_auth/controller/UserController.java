package org.rizki.fintech.service_auth.controller;

import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.dto.UserResponse;
import org.rizki.fintech.service_auth.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.findAllUser();
    }

}
