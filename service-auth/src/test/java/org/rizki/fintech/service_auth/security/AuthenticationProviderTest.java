package org.rizki.fintech.service_auth.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rizki.fintech.service_auth.common.constant.LoginMethod;
import org.rizki.fintech.service_auth.core.security.login.LoginAuthenticationToken;
import org.rizki.fintech.service_auth.core.security.provider.OtpAuthenticationProvider;
import org.rizki.fintech.service_auth.module.user.application.port.UserPort;
import org.rizki.fintech.service_auth.module.user.application.service.OtpService;
import org.rizki.fintech.service_auth.module.user.domain.dto.RoleView;
import org.rizki.fintech.service_auth.module.user.domain.dto.UserView;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationProviderTest {

    @Mock
    UserPort userPort;

    @Mock
    OtpService otpService;

    @InjectMocks
    OtpAuthenticationProvider provider;

    @Test
    void shouldAuthenticateWithValidOtp() {
        UserView user = new UserView(
                1L,
                "rizki",
                "08123",
                "mail",
                LocalDateTime.now(),
                List.of(new RoleView(1L, "admin", ""))
        );

        when(userPort.findByPhoneNumber("08123"))
                .thenReturn(Optional.of(user));
        when(otpService.validateOtp(1L, "123456"))
                .thenReturn(true);

        LoginAuthenticationToken token =
                new LoginAuthenticationToken(
                        "08123",
                        "123456",
                        LoginMethod.PASSWORD
                );

        Authentication result = provider.authenticate(token);

        assertTrue(result.isAuthenticated());
    }
}
