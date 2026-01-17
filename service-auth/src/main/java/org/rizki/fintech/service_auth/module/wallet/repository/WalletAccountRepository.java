package org.rizki.fintech.service_auth.module.wallet.repository;

import org.rizki.fintech.service_auth.module.wallet.entity.WalletAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WalletAccountRepository extends JpaRepository<WalletAccount, UUID> {

    Optional<WalletAccount> findByAccountNumber(String accountNumber);
}
