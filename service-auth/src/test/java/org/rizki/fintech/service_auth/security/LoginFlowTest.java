package org.rizki.fintech.service_auth.security;

import org.junit.jupiter.api.Test;
import org.rizki.fintech.service_auth.common.config.JwtConfigProps;
import org.rizki.fintech.service_auth.common.config.SecurityConfig;
import org.rizki.fintech.service_auth.core.security.jwt.JwtAuthenticationEntryPoint;
import org.rizki.fintech.service_auth.core.security.jwt.JwtTokenProvider;
import org.rizki.fintech.service_auth.core.security.provider.OtpAuthenticationProvider;
import org.rizki.fintech.service_auth.core.security.provider.PasswordAuthenticationProvider;
import org.rizki.fintech.service_auth.module.user.application.port.CredentialPort;
import org.rizki.fintech.service_auth.module.user.application.port.UserPort;
import org.rizki.fintech.service_auth.module.user.application.service.OtpService;
import org.rizki.fintech.service_auth.module.user.application.service.UserService;
import org.rizki.fintech.service_auth.module.user.constant.CredentialType;
import org.rizki.fintech.service_auth.module.user.domain.dto.CredentialView;
import org.rizki.fintech.service_auth.module.user.domain.dto.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import({
        SecurityConfig.class,
        OtpAuthenticationProvider.class,
        PasswordAuthenticationProvider.class,
        JwtTokenProvider.class
})
@AutoConfigureMockMvc(addFilters = true)
public class LoginFlowTest {

    @Autowired
    MockMvc mockMvc;


    @MockBean
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    UserPort userPort;

    @MockBean
    CredentialPort credentialPort;

    @MockBean
    OtpService otpService;

    @MockBean
    UserService userService;

    @MockBean
    JwtConfigProps configProps;

    @MockBean
    JwtTokenProvider jwtTokenProvider;


    @Test
    void loginOtp_success() throws Exception {

        when(userPort.findByPhoneNumber("08123"))
                .thenReturn(Optional.of(UserView.builder().build()));
        when(otpService.validateOtp(any(), eq("123456")))
                .thenReturn(true);

        when(jwtTokenProvider.createAccessToken(any(), any(), any()))
                .thenReturn("dummy-jwt-token");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                  "identifier": "08123",
                  "method": "OTP",
                  "credential": "123456"
                }
            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void loginPassword_success() throws Exception {

        String rawPassword = "123456";
        String hashed = new BCryptPasswordEncoder().encode(rawPassword);

        when(userPort.findByUsername("rizki"))
                .thenReturn(Optional.of(
                        UserView.builder()
                                .id(1L)
                                .username("rizki")
                                .build()
                ));

        when(credentialPort.findByUserIdAndCredentialType(1L, CredentialType.PASSWORD))
                .thenReturn(Optional.of(CredentialView.builder().secret(hashed).build()));

        when(jwtTokenProvider.createAccessToken(any(), any(), any()))
                .thenReturn("dummy-jwt-token");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                  "identifier": "rizki",
                  "method": "PASSWORD",
                  "credential": "123456"
                }
            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }
}
