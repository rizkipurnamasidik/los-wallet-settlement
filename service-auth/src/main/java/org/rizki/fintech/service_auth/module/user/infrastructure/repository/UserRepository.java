package org.rizki.fintech.service_auth.module.user.infrastructure.repository;

import org.rizki.fintech.service_auth.module.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndIsActiveIsTrue(String username);

    Optional<User> findByPhoneNumberAndIsActiveIsTrue(String phoneNumber);

    List<User> findByIsActiveIsTrue();
}
