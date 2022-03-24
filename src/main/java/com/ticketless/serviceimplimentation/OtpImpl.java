package com.ticketless.serviceimplimentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketless.model.OneTimePassword;
import com.ticketless.model.Users;
import com.ticketless.repository.OtpRepo;
import com.ticketless.service.OtpService;
import com.ticketless.service.UsersService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class OtpImpl implements OtpService {

    @Autowired
    private UsersService userSerice;
    
    @Autowired 
    private OtpRepo otpRepo;
    
    
    @Override
    public boolean createotp(String mobile) {
        
        try {
            Users users = userSerice.getUserByMobile(mobile);
            
            OneTimePassword oneTimePassword1 = otpRepo.findByusers(users);
            
            if(oneTimePassword1!=null) {
                otpRepo.delete(oneTimePassword1);
            }
            
            OneTimePassword oneTimePassword = new OneTimePassword(users);
            
            OneTimePassword otp = otpRepo.save(oneTimePassword);
            Twilio.init("ACd4239cce1ba6582685cd0ba7023d57e9", "ce2f09eccd1c3870873dbf6bac0f791a");   
          Message message = Message.creator(
          new PhoneNumber("+91"+mobile),    
          new PhoneNumber("+16812532627"),
          "Verification Code :" + oneTimePassword.getOtpValue())
          .create();
          System.out.println(message.getStatus());
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean getOtpByValue(String otp,String mobile) {

        try {
            OneTimePassword oneTimePassword =  otpRepo.findByotpValue(otp);
            
            if(oneTimePassword == null) {
                return false;
            }else {
                Users users = oneTimePassword.getUsers();
                
                if(users.getMobileNo().equals(mobile)) {
                    otpRepo.delete(oneTimePassword); 
                    return true;
                }
                else return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

}
































