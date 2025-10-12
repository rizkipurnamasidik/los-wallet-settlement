package org.rizki.fintech.service_auth.module.user.repository;

import org.rizki.fintech.service_auth.module.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndIsActiveIsTrue(String username);

    List<User> findByIsActiveIsTrue();
}
