package org.rizki.fintech.service_auth.module.auth.dto;

import org.rizki.fintech.service_auth.common.constant.LoginMethod;

public record LoginRequest(

        String identifier,

        LoginMethod method,

        String credential
) {
}
