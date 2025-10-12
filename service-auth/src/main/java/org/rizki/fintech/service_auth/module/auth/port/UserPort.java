package org.rizki.fintech.service_auth.module.auth.port;

import org.rizki.fintech.service_auth.module.auth.dto.AuthUser;

import java.util.Optional;

public interface UserPort {

    Optional<AuthUser> findByUsername(String username);
}
