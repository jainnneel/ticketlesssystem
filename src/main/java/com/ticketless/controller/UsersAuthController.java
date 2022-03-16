package com.ticketless.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ticketless.dto.UserDto;
import com.ticketless.requestdto.AuthMobileDto;
import com.ticketless.resposedto.ResponseEntity;
import com.ticketless.security.Jwtutil;
import com.ticketless.service.UsersService;

@RestController
public class UsersAuthController {

    @Autowired
    private UsersService usersService;
    
    @Autowired
    private Jwtutil jwtutil;
    
    @PostMapping(value = "/auth/mobile",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity mobileAuth(@RequestBody AuthMobileDto  mobile) {
        
        ResponseEntity entity = new ResponseEntity();
        
        if (usersService.createUser(new UserDto("", mobile.getMobile()))) {
            entity.setData("otp send successfully");
            entity.setStatus("done");
            entity.setHttpStatus(HttpStatus.OK);
            return entity;  
        }else {
            entity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            entity.setData("try again");
            return entity;
        }
    }
    
    @GetMapping(value = "/auth/successlogin")
    public ResponseEntity loginSuccess(@AuthenticationPrincipal UserDetails principal,HttpServletResponse response) {
        ResponseEntity entity = new ResponseEntity();
        Cookie cookie = new Cookie("platform","mobile");

        // expires in 7 days
        cookie.setMaxAge(7 * 24 * 60 * 60);

        // optional properties
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        // add cookie to response
        response.addCookie(cookie);
        String generateToken = jwtutil.generateToken(principal.getUsername());
        entity.setHttpStatus(HttpStatus.OK);
        entity.setToken(generateToken);
        return entity;
    }
    
    @GetMapping(value = "/auth/authfail")
    public ResponseEntity loginfail() {
        ResponseEntity entity = new ResponseEntity();
        entity.setHttpStatus(HttpStatus.OK);
        entity.setData("Authentication failed");
        entity.setStatus("failed");
        entity.setToken(null);
        return entity;
    }
    
    @GetMapping(value = "/auth/authreq")
    public ResponseEntity authfail() {
        ResponseEntity entity = new ResponseEntity();
        entity.setHttpStatus(HttpStatus.OK);
        entity.setData("Authentication required.. add token");
        entity.setStatus("failed");
        entity.setToken(null);
        return entity;
    }
    
    
}












































