package org.rizki.fintech.service_auth.core.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.rizki.fintech.service_auth.common.config.JwtConfigProps;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private final JwtConfigProps jwtConfigProps;
    private final SecretKey secretKey;

    public JwtTokenProvider(JwtConfigProps jwtConfigProps) {

        this.jwtConfigProps = jwtConfigProps;

        this.secretKey = Keys.hmacShaKeyFor(jwtConfigProps.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String createAccessToken(
            String subject,
            List<String> roles,
            Map<String, Object> claims
    ) {
        Instant now = Instant.now();

        JwtBuilder jwtBuilder = Jwts.builder()
                .subject(subject)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(jwtConfigProps.getAccessTokenExpirationMinutes(), ChronoUnit.MINUTES)))
                .issuer(jwtConfigProps.getIssuer())
                .claim("roles", roles);

        if (claims != null) jwtBuilder.claims(claims);

        return jwtBuilder.signWith(secretKey).compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public Jws<Claims> parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
    }

    public String getSubject(String token) {
        return parseClaims(token).getPayload().getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> getRoles(String token) {
        Object roles = parseClaims(token).getPayload().get("roles");
        return roles instanceof List ? (List<String>) roles : List.of();
    }


}
