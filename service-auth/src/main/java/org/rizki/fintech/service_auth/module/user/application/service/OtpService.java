package org.rizki.fintech.service_auth.module.user.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpService {

    //TODO: Implement OTP

    public void generateOtp(String phoneNumber) {

    }

    public Boolean validateOtp(Long userId, String otp){

        return Boolean.TRUE;
    }

    public void setExpiredOtp(Long userId) {

    }
}
