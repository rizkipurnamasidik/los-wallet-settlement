package org.rizki.fintech.service_auth.dto;

import lombok.Builder;

@Builder
public record RoleResponse(
        Long id,

        String name,

        String description) {
}
