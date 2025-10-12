package org.rizki.fintech.service_auth.module.auth.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AuthUser (
        Long id,
        String username,
        String email,
        List<AuthRole> roles
) {

}
