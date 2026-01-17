package org.rizki.fintech.service_auth.core.security.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.common.config.JwtConfigProps;
import org.rizki.fintech.service_auth.core.security.UserPrincipal;
import org.rizki.fintech.service_auth.core.security.jwt.JwtTokenProvider;
import org.rizki.fintech.service_auth.module.auth.dto.LoginResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtConfigProps jwtConfigProps;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String accessToken = jwtTokenProvider.createAccessToken(user.getUsername(), roles, null);

        LoginResponse loginResponse = new LoginResponse(accessToken, jwtConfigProps.getAccessTokenExpirationMinutes());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), loginResponse);
    }
}
