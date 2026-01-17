package org.rizki.fintech.service_auth.module.wallet.service;

import lombok.RequiredArgsConstructor;
import org.rizki.fintech.service_auth.module.wallet.dto.TopUpResponse;
import org.rizki.fintech.service_auth.module.wallet.repository.WalletAccountRepository;
import org.rizki.fintech.service_auth.module.wallet.repository.WalletTransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TopUpService {

    private final WalletAccountRepository walletAccountRepository;

    private final WalletTransactionRepository walletTransactionRepository;

    public TopUpResponse execute(Long userId, BigDecimal amount, String idempotencyKey) {

//        walletAccountRepository.findById();

        return new TopUpResponse("", "");
    }
}
