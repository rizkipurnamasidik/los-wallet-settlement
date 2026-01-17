package org.rizki.fintech.service_auth.module.user.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UserResponse(
        Long id,
        String username,
        String email,
        List<RoleResponse> roles
) {
}
