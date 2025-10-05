package org.rizki.fintech.service_auth.dto;

public record LoginRequest(
        String username,

        String password
) {
}
