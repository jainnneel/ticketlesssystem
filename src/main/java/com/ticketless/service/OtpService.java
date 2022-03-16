package com.ticketless.service;

import org.springframework.stereotype.Service;

import com.ticketless.model.OneTimePassword;

@Service
public interface OtpService  {

    boolean createotp(String mobile);

    boolean getOtpByValue(String otp, String mobile);
}
