-- wallet
CREATE TABLE wallet
(
    id             UUID PRIMARY KEY,
    user_id        BIGINT           NOT NULL UNIQUE,
    balance        numeric(18, 2) NOT NULL DEFAULT 0,
    frozen_balance numeric(18, 2) NOT NULL DEFAULT 0,
    currency       varchar(3)     NOT NULL DEFAULT 'IDR',
    updated_at     timestamptz             DEFAULT now()
);

-- transaction (immutable audit)
CREATE TABLE wallet_transaction
(
    id              UUID PRIMARY KEY,
    wallet_id       UUID           NOT NULL,
    type            varchar(20)    NOT NULL, -- TOPUP, TRANSFER, WITHDRAW
    amount          numeric(18, 2) NOT NULL,
    status          varchar(20)    NOT NULL, -- PENDING, SUCCESS, FAILED
    reference_no    varchar(128),
    idempotency_key varchar(128),
    metadata        jsonb,
    created_at      timestamptz DEFAULT now()
);

CREATE INDEX idx_tx_wallet ON wallet_transaction (wallet_id);
CREATE UNIQUE INDEX ux_tx_idempotency ON wallet_transaction (idempotency_key) WHERE idempotency_key IS NOT NULL;

-- ledger_entry (double-entry)
CREATE TABLE ledger_entry
(
    id             UUID PRIMARY KEY,
    debit_account  varchar(100)   NOT NULL,
    credit_account varchar(100)   NOT NULL,
    amount         numeric(18, 2) NOT NULL,
    trx_ref        UUID           NOT NULL,
    description    text,
    created_at     timestamptz DEFAULT now()
);
