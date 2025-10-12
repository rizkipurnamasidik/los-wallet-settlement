package org.rizki.fintech.service_auth.module.user.adapter;

import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.module.auth.dto.AuthRole;
import org.rizki.fintech.service_auth.module.auth.dto.AuthUser;
import org.rizki.fintech.service_auth.module.auth.port.UserPort;
import org.rizki.fintech.service_auth.module.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAdapter implements UserPort {

    private final UserRepository userRepository;

    @Override
    public Optional<AuthUser> findByUsername(String username) {
        return userRepository.findByUsernameAndIsActiveIsTrue(username)
                .map(user -> {
                    List<AuthRole> authRoles = user.getRoles().stream()
                            .map(role -> AuthRole.builder()
                                    .id(role.getId())
                                    .name(role.getName())
                                    .description(role.getDescription())
                                    .build()
                            ).toList();

                    return AuthUser.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .roles(authRoles)
                            .email(user.getEmail())
                            .build();
                });
    }
}
