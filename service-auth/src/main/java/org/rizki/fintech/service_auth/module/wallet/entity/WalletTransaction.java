package org.rizki.fintech.service_auth.module.wallet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.rizki.fintech.service_auth.common.base.BaseTransactionEntity;
import org.rizki.fintech.service_auth.module.wallet.constant.WalletTransactionStatus;
import org.rizki.fintech.service_auth.module.wallet.constant.WalletTransactionType;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallet_transaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WalletTransaction extends BaseTransactionEntity {

    @Column(name = "wallet_id")
    UUID walletId;

    @Column(name = "reference_no", nullable = false, length = 128)
    String referenceNumber;

    @Column(name = "idempotency_key", nullable = false, length = 128)
    String idempotencyKey;

    @Column(nullable = false, precision = 18, scale = 2)
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column
    WalletTransactionType type;

    @Enumerated(EnumType.STRING)
    @Column
    WalletTransactionStatus status;

    @Column
    String metadata;
}
