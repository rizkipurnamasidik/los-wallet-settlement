package org.rizki.fintech.service_auth.service;

import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.config.JwtConfigProps;
import org.rizki.fintech.service_auth.dto.LoginRequest;
import org.rizki.fintech.service_auth.dto.LoginResponse;
import org.rizki.fintech.service_auth.dto.RegisterRequest;
import org.rizki.fintech.service_auth.dto.RegisterResponse;
import org.rizki.fintech.service_auth.entity.Role;
import org.rizki.fintech.service_auth.entity.User;
import org.rizki.fintech.service_auth.repository.RoleRepository;
import org.rizki.fintech.service_auth.repository.UserRepository;
import org.rizki.fintech.service_auth.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtConfigProps jwtConfigProps;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        User user = userRepository.findByUsernameAndIsActiveIsTrue(loginRequest.username()).orElseThrow();

        List<String> roles = user.getRoles().stream().map(Role::getName).toList();

        String accessToken = jwtTokenProvider.createAccessToken(user.getUsername(), roles, null);

        return new LoginResponse(accessToken, jwtConfigProps.getAccessTokenExpirationMinutes());
    }

    public RegisterResponse register(RegisterRequest request) {

        //TODO: Validate

        Role role = roleRepository.findByIdAndIsActiveIsTrue(request.roleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User newUser = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .roles(List.of(role))
                .build();

        userRepository.save(newUser);

        return new RegisterResponse("SUCCESS");
    }
}
