package com.ticketless.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.ticketless.model.Users;
import com.ticketless.service.OtpService;
import com.ticketless.service.UsersService;

@Component
public class CustomAuthenticationprovider implements AuthenticationProvider {

    
    @Autowired
    private UsersService userService;
    
    @Autowired
    private OtpService otpService;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       
        UsernamePasswordAuthenticationToken authenticationToken = null;
        
        String mobile = authentication.getName().toLowerCase();
        String otp = authentication.getCredentials().toString();
        
        Users users = userService.getUserByMobile(mobile);
        
        if(users!=null) {
            
            boolean oneTimePassword = otpService.getOtpByValue(otp,mobile);
            
            if(oneTimePassword) {
                List<GrantedAuthority> grantedAuths = new ArrayList<>();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                authenticationToken = new UsernamePasswordAuthenticationToken(new User(mobile, "", grantedAuths), "",grantedAuths);
            }
        }
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    
}
