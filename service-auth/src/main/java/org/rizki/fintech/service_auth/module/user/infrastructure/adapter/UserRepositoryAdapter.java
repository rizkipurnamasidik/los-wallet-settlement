package org.rizki.fintech.service_auth.module.user.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.module.user.application.port.UserPort;
import org.rizki.fintech.service_auth.module.user.domain.dto.RoleView;
import org.rizki.fintech.service_auth.module.user.domain.dto.UserView;
import org.rizki.fintech.service_auth.module.user.domain.entity.User;
import org.rizki.fintech.service_auth.module.user.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserPort {

    private final UserRepository userRepository;

    @Override
    public Optional<UserView> findByUsername(String username) {
        return userRepository.findByUsernameAndIsActiveIsTrue(username)
                .map(this::mapFromUser);
    }

    @Override
    public Optional<UserView> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumberAndIsActiveIsTrue(phoneNumber)
                .map(this::mapFromUser);
    }

    private UserView mapFromUser(User user) {
        List<RoleView> authRoles = user.getRoles().stream()
                .map(role -> RoleView.builder()
                        .id(role.getId())
                        .name(role.getName())
                        .description(role.getDescription())
                        .build()
                ).toList();

        return UserView.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(authRoles)
                .email(user.getEmail())
                .build();
    }
}
