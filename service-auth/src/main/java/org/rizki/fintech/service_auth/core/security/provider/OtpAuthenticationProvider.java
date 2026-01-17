package org.rizki.fintech.service_auth.core.security.provider;

import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.common.constant.LoginMethod;
import org.rizki.fintech.service_auth.core.security.UserPrincipal;
import org.rizki.fintech.service_auth.core.security.login.LoginAuthenticationToken;
import org.rizki.fintech.service_auth.module.user.application.port.UserPort;
import org.rizki.fintech.service_auth.module.user.application.service.OtpService;
import org.rizki.fintech.service_auth.module.user.domain.dto.UserView;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private final UserPort userPort;

    private final OtpService otpService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        LoginAuthenticationToken authenticationToken = (LoginAuthenticationToken) authentication;

        if (authenticationToken.getMethod() != LoginMethod.OTP) {
            return null;
        }

        UserView user = userPort.findByPhoneNumber(authenticationToken.getPrincipal().toString())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid phone number"));

        Boolean valid = otpService.validateOtp(user.id(), authenticationToken.getCredential());

        if (!valid) {
            otpService.setExpiredOtp(user.id());
            throw new BadCredentialsException("Invalid OTP");
        }

        UserPrincipal userPrincipal = new UserPrincipal(user);

        return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return LoginAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
