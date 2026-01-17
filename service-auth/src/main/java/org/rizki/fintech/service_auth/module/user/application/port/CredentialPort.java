package org.rizki.fintech.service_auth.module.user.application.port;

import org.rizki.fintech.service_auth.module.user.constant.CredentialType;
import org.rizki.fintech.service_auth.module.user.domain.dto.CredentialView;

import java.util.Optional;

public interface CredentialPort {

    Optional<CredentialView> findByUserIdAndCredentialType(Long userId, CredentialType credentialType);
}
