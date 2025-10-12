package org.rizki.fintech.service_auth.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app.jwt")
@Getter
@Setter
public class JwtConfigProps {

    private String issuer;

    private String secret;

    private long accessTokenExpirationMinutes;
}
