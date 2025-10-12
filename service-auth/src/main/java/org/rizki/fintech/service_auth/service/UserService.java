package org.rizki.fintech.service_auth.service;

import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.dto.RoleResponse;
import org.rizki.fintech.service_auth.dto.UserResponse;
import org.rizki.fintech.service_auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
}
