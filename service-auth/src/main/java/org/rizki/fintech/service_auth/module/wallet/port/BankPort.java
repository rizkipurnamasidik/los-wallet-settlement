package org.rizki.fintech.service_auth.module.wallet.port;

import org.rizki.fintech.service_auth.module.wallet.dto.BankResponse;

import java.math.BigDecimal;

public interface BankPort {

    BankResponse debit(String accountNumber, BigDecimal amount, String referenceNumber);
}
