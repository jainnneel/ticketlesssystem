package com.ticketless.model;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OneTimePassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long otpId;
    
    private String otpValue;
    
    @JsonIgnore
    @OneToOne
    private Users users;
    
    private Date otpDate;
    
    private Time otpTime;

    public OneTimePassword(Users users) {
        this.users = users;
        this.otpValue = generateOtp();
        System.out.println(this.otpValue);
        this.otpDate = Date.valueOf(LocalDate.now());
        this.otpTime = Time.valueOf(LocalTime.now());
    
    }

    public OneTimePassword() {
        super();
    }

    public Long getOtpId() {
        return otpId;
    }

    public void setOtpId(Long otpId) {
        this.otpId = otpId;
    }

    public String getOtpValue() {
        return otpValue;
    }

    public void setOtpValue(String otpValue) {
        this.otpValue = otpValue;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Date getOtpDate() {
        return otpDate;
    }

    public void setOtpDate(Date otpDate) {
        this.otpDate = otpDate;
    }

    public Time getOtpTime() {
        return otpTime;
    }

    public void setOtpTime(Time otpTime) {
        this.otpTime = otpTime;
    }

    @Override
    public String toString() {
        return "OneTimePassword [otpId=" + otpId + ", otpValue=" + otpValue + ", users=" + users + ", otpDate="
                + otpDate + ", otpTime=" + otpTime + "]";
    }
    
    private String  generateOtp() {
        StringBuilder generatedOTP = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        try {

            secureRandom = SecureRandom.getInstance(secureRandom.getAlgorithm());

            for (int i = 0; i < 6; i++) {
                generatedOTP.append(secureRandom.nextInt(9));
            }
            if(generatedOTP.charAt(0)=='0') {
                generatedOTP.setCharAt(0, '7');
            }
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedOTP.toString();
    }
    
}
