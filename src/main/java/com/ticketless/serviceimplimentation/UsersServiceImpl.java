package com.ticketless.serviceimplimentation;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ticketless.dto.UserDto;
import com.ticketless.model.Users;
import com.ticketless.repository.UsersRepo;
import com.ticketless.service.OtpService;
import com.ticketless.service.UsersService;

@Component
public class UsersServiceImpl  implements UsersService{

    
    @Autowired
    private UsersRepo usersRepo;
    
    @Autowired
    private OtpService otpService;
    
    @Override
    public boolean createUser(UserDto userdto) {
        try {
            Users users = getUserByMobile(userdto.getMobile());
            
            if(users == null) {
                Users users1 = new Users();
                users1.setMobileNo(userdto.getMobile());
                users1.setDateOfJoining(Date.valueOf(LocalDate.now()));
                Users save = usersRepo.save(users1);
                otpService.createotp(save.getMobileNo());
            }else {
                otpService.createotp(userdto.getMobile());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Users getUserByMobile(String mobile) {
        try {
            Users users =  usersRepo.findUserByMobile(mobile);
            if (users != null) {
                return users;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
