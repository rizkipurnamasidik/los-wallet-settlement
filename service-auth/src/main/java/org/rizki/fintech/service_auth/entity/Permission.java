package org.rizki.fintech.service_auth.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "permissions")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class Permission extends BaseEntity {

    @Column
    String name;

}
