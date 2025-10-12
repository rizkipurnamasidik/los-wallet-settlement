package org.rizki.fintech.service_auth.module.user.dto;

public record RegisterRequest(
        String username,

        String password,

        String confirmPassword,

        String email,

        Long roleId
) {
}
