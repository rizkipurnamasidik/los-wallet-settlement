package org.rizki.fintech.service_auth.repository;

import org.rizki.fintech.service_auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByIdAndIsActiveIsTrue(Long id);
}
