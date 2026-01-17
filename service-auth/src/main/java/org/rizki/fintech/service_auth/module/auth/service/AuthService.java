package org.rizki.fintech.service_auth.module.auth.service;

import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.common.config.JwtConfigProps;
import org.rizki.fintech.service_auth.core.security.jwt.JwtTokenProvider;
import org.rizki.fintech.service_auth.module.auth.dto.LoginRequest;
import org.rizki.fintech.service_auth.module.auth.dto.LoginResponse;
import org.rizki.fintech.service_auth.module.user.application.port.UserPort;
import org.rizki.fintech.service_auth.module.user.domain.dto.RoleView;
import org.rizki.fintech.service_auth.module.user.domain.dto.UserView;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtConfigProps jwtConfigProps;

    private final UserPort userPort;


    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.identifier(), loginRequest.credential()));

        UserView user = userPort.findByUsername(loginRequest.identifier()).orElseThrow();

        List<String> roles = user.roles().stream().map(RoleView::name).toList();

        String accessToken = jwtTokenProvider.createAccessToken(user.username(), roles, null);

        return new LoginResponse(accessToken, jwtConfigProps.getAccessTokenExpirationMinutes());
    }

}
