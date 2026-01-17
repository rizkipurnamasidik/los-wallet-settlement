package org.rizki.fintech.service_auth.module.user.application.service;

import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.module.user.domain.dto.RegisterRequest;
import org.rizki.fintech.service_auth.module.user.domain.dto.RegisterResponse;
import org.rizki.fintech.service_auth.module.user.domain.dto.RoleResponse;
import org.rizki.fintech.service_auth.module.user.domain.dto.UserResponse;
import org.rizki.fintech.service_auth.module.user.domain.entity.Role;
import org.rizki.fintech.service_auth.module.user.domain.entity.User;
import org.rizki.fintech.service_auth.module.user.infrastructure.repository.RoleRepository;
import org.rizki.fintech.service_auth.module.user.infrastructure.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> findAllUser() {
        return userRepository.findByIsActiveIsTrue()
                .stream()
                .map(user -> {

                            List<RoleResponse> roles = user.getRoles().stream().map(role -> RoleResponse.builder()
                                    .id(role.getId())
                                    .name(role.getName())
                                    .description(role.getDescription())
                                    .build()
                            ).toList();

                            return UserResponse.builder()
                                    .id(user.getId())
                                    .username(user.getUsername())
                                    .email(user.getEmail())
                                    .roles(roles)
                                    .build();

                        }
                )
                .toList();
    }

    public RegisterResponse register(RegisterRequest request) {

        //TODO: Validate

        Role role = roleRepository.findByIdAndIsActiveIsTrue(request.roleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User newUser = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .roles(List.of(role))
                .build();

        User user = userRepository.save(newUser);

        return new RegisterResponse(user.getId(), "SUCCESS");
    }
}
