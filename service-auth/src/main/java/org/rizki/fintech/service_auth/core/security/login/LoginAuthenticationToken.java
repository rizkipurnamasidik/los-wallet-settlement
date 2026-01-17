package org.rizki.fintech.service_auth.core.security.login;

import lombok.Getter;
import org.rizki.fintech.service_auth.common.constant.LoginMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Getter
public class LoginAuthenticationToken extends AbstractAuthenticationToken {

    private final String identifier;
    private final String credential;
    private final LoginMethod method;

    public LoginAuthenticationToken(
            String identifier,
            String credential,
            LoginMethod method
    ) {
        super(null);
        this.identifier = identifier;
        this.credential = credential;
        this.method = method;
        setAuthenticated(false);
    }


    @Override
    public Object getCredentials() {
        return credential;
    }

    @Override
    public Object getPrincipal() {
        return identifier;
    }
}
