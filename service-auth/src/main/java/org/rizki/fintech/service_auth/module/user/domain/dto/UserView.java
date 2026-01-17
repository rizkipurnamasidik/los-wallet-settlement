package org.rizki.fintech.service_auth.module.user.domain.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UserView(
        Long id,
        String username,
        String phoneNumber,
        String email,
        LocalDateTime lastLogin,
        List<RoleView> roles
) {
}
