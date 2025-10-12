package org.rizki.fintech.service_auth.module.auth.service;

import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.common.config.JwtConfigProps;
import org.rizki.fintech.service_auth.module.auth.dto.AuthRole;
import org.rizki.fintech.service_auth.module.auth.dto.AuthUser;
import org.rizki.fintech.service_auth.module.auth.dto.LoginRequest;
import org.rizki.fintech.service_auth.module.auth.dto.LoginResponse;
import org.rizki.fintech.service_auth.module.user.adapter.UserAdapter;
import org.rizki.fintech.service_auth.module.user.dto.RegisterRequest;
import org.rizki.fintech.service_auth.module.user.dto.RegisterResponse;
import org.rizki.fintech.service_auth.module.user.entity.Role;
import org.rizki.fintech.service_auth.module.user.entity.User;
import org.rizki.fintech.service_auth.module.user.repository.RoleRepository;
import org.rizki.fintech.service_auth.module.user.repository.UserRepository;
import org.rizki.fintech.service_auth.core.security.JwtTokenProvider;
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

    private final UserAdapter userAdapter;


    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        AuthUser user = userAdapter.findByUsername(loginRequest.username()).orElseThrow();

        List<String> roles = user.roles().stream().map(AuthRole::name).toList();

        String accessToken = jwtTokenProvider.createAccessToken(user.username(), roles, null);

        return new LoginResponse(accessToken, jwtConfigProps.getAccessTokenExpirationMinutes());
    }

}
