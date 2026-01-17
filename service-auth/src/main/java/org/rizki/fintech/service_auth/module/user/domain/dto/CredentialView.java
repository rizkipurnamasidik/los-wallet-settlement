package org.rizki.fintech.service_auth.module.user.domain.dto;

import lombok.Builder;
import org.rizki.fintech.service_auth.module.user.constant.CredentialType;

@Builder
public record CredentialView (
        Long userId,

        CredentialType credentialType,

        String secret
){
}
