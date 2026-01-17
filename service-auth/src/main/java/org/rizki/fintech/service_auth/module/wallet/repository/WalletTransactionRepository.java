package org.rizki.fintech.service_auth.module.wallet.repository;

import org.rizki.fintech.service_auth.module.wallet.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, UUID> {
}