package org.rizki.fintech.service_auth.core.security.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.rizki.fintech.service_auth.module.auth.dto.LoginRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;


public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public  LoginAuthenticationFilter(
            ObjectMapper objectMapper
    ) {
        super("/api/auth/login");
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException, IOException {

        LoginRequest req = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

        LoginAuthenticationToken loginAuthenticationToken = new LoginAuthenticationToken(req.identifier(), req.credential(), req.method());
        return getAuthenticationManager().authenticate(loginAuthenticationToken);
    }
}
