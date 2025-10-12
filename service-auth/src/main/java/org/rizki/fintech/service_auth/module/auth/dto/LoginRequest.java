package org.rizki.fintech.service_auth.module.auth.dto;

public record LoginRequest(
        String username,

        String password
) {
}
