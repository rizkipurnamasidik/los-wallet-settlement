package org.rizki.fintech.service_auth.dto;

public record LoginResponse(
        String token,

        long tokenExpiration
) {
}
