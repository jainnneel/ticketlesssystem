package com.ticketless.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketless.model.OneTimePassword;
import com.ticketless.model.Users;

@Repository
public interface OtpRepo extends JpaRepository<OneTimePassword, Long> {

    OneTimePassword findByusers(Users users);

    OneTimePassword findByotpValue(String otp);

}
