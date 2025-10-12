package org.rizki.fintech.service_auth.module.auth.dto;

import lombok.Builder;

@Builder
public record AuthRole(
        Long id,

        String name,

        String description
) {
}
