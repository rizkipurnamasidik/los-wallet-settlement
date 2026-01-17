package org.rizki.fintech.service_auth.module.user.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.rizki.fintech.service_auth.common.base.BaseEntity;
import org.rizki.fintech.service_auth.module.user.constant.CredentialType;

import java.time.LocalDateTime;

@Entity
@Table(name = "credentials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Credential extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private CredentialType type;

    private String secret;

    private String status;

    private LocalDateTime expiredUntil;
}
