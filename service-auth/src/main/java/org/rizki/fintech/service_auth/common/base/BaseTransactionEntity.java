package org.rizki.fintech.service_auth.common.base;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseTransactionEntity extends CreatedUpdatedBase {

    @Id
    UUID id;
}
