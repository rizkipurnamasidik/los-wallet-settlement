package org.rizki.fintech.service_auth.core.security.provider;


import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.common.constant.LoginMethod;
import org.rizki.fintech.service_auth.core.security.UserPrincipal;
import org.rizki.fintech.service_auth.core.security.login.LoginAuthenticationToken;
import org.rizki.fintech.service_auth.module.user.application.port.CredentialPort;
import org.rizki.fintech.service_auth.module.user.application.port.UserPort;
import org.rizki.fintech.service_auth.module.user.constant.CredentialType;
import org.rizki.fintech.service_auth.module.user.domain.dto.CredentialView;
import org.rizki.fintech.service_auth.module.user.domain.dto.UserView;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserPort userPort;

    private final CredentialPort credentialPort;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        LoginAuthenticationToken authenticationToken = (LoginAuthenticationToken) authentication;

        if (authenticationToken.getMethod() != LoginMethod.PASSWORD) {
            return null;
        }

        UserView user = userPort.findByUsername(authenticationToken.getPrincipal().toString())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        CredentialView credential = credentialPort.findByUserIdAndCredentialType(user.id(), CredentialType.PASSWORD)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));


        if(!passwordEncoder.matches(authenticationToken.getCredential(), credential.secret())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        UserPrincipal userPrincipal = new UserPrincipal(user);

        return new UsernamePasswordAuthenticationToken(userPrincipal, null, authenticationToken.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return LoginAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
