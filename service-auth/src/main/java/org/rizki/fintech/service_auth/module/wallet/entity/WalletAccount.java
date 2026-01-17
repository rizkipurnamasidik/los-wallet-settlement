package org.rizki.fintech.service_auth.module.wallet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.rizki.fintech.service_auth.common.base.BaseTransactionEntity;
import org.rizki.fintech.service_auth.module.user.domain.entity.User;
import org.rizki.fintech.service_auth.module.wallet.constant.WalletAccountStatus;

@Entity
@Table(name = "wallet_account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WalletAccount extends BaseTransactionEntity {

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id")
    User userId;

    @Column(name = "account_number")
    String accountNumber;

    @Column(name = "account_type")
    String accountType;

    @Enumerated(EnumType.STRING)
    @Column
    WalletAccountStatus status;

}
