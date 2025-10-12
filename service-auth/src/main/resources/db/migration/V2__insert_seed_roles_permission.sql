INSERT INTO roles (name)
VALUES ('ADMIN'),
       ('USER')
ON CONFLICT (name) DO NOTHING;

INSERT INTO permissions (name)
VALUES ('USER_READ'),
       ('USER_WRITE'),
       ('PAYMENT_CREATE'),
       ('PAYMENT_APPROVE')
ON CONFLICT (name) DO NOTHING;
