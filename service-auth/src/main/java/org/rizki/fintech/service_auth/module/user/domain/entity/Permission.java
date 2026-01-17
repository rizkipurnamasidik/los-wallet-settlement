package org.rizki.fintech.service_auth.module.user.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.rizki.fintech.service_auth.common.base.BaseEntity;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseEntity {

    @Column
    String name;

}
