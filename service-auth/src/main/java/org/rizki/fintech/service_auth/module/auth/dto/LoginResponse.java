package org.rizki.fintech.service_auth.module.auth.dto;

public record LoginResponse(
        String token,

        long tokenExpiration
) {
}
