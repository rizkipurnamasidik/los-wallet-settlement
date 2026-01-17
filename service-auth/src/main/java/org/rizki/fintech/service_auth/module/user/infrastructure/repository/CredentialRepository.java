package org.rizki.fintech.service_auth.module.user.infrastructure.repository;

import org.rizki.fintech.service_auth.module.user.constant.CredentialType;
import org.rizki.fintech.service_auth.module.user.domain.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    Optional<Credential> findFirstByUserIdAndType(Long userId, CredentialType type);
}
