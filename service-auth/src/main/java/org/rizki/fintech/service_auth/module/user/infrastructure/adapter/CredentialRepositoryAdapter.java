package org.rizki.fintech.service_auth.module.user.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.module.user.application.port.CredentialPort;
import org.rizki.fintech.service_auth.module.user.constant.CredentialType;
import org.rizki.fintech.service_auth.module.user.domain.dto.CredentialView;
import org.rizki.fintech.service_auth.module.user.infrastructure.repository.CredentialRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CredentialRepositoryAdapter implements CredentialPort {

    private final CredentialRepository credentialRepository;


    @Override
    public Optional<CredentialView> findByUserIdAndCredentialType(Long userId, CredentialType credentialType) {
        return credentialRepository.findFirstByUserIdAndType(userId, credentialType)
                .map(cred -> new CredentialView(cred.getUser().getId(), cred.getType(), cred.getSecret()));
    }
}
