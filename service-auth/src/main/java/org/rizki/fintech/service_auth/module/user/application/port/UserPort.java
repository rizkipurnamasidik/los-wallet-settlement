package org.rizki.fintech.service_auth.module.user.application.port;

import org.rizki.fintech.service_auth.module.user.domain.dto.UserView;

import java.util.Optional;

public interface UserPort {

    Optional<UserView> findByUsername(String username);

    Optional<UserView> findByPhoneNumber(String phoneNumber);
}
