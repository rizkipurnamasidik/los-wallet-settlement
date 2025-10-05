package org.rizki.fintech.service_auth.repository;

import org.rizki.fintech.service_auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndIsActiveIsTrue(String username);
}
