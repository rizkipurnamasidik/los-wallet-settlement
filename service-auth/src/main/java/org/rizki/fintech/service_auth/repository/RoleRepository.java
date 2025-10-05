package org.rizki.fintech.service_auth.repository;

import org.rizki.fintech.service_auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
