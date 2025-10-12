package org.rizki.fintech.service_auth.dto;

public record RegisterRequest(
        String username,

        String password,

        String confirmPassword,

        String email,

        Long roleId
) {
}
