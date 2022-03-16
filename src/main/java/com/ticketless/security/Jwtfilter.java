package com.ticketless.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ticketless.model.Users;
import com.ticketless.service.UsersService;

@Component
public class Jwtfilter extends OncePerRequestFilter {

    @Autowired
    private Jwtutil jwtutil;
    
    @Autowired
    private UsersService userService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        final String header = request.getHeader("Authorization");
        
        String jwt=null;
        String mobile = null;
        
        if(header!=null && header.startsWith("Bearer ")) {
            jwt=header.substring(7);
            mobile=jwtutil.getUsernameFromToken(jwt);
        }
        
        if(mobile!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            Users ud = userService.getUserByMobile(mobile);
            if(ud!= null && Boolean.TRUE.equals(jwtutil.validateToken(jwt,mobile))) {
                List<GrantedAuthority> grantedAuths = new ArrayList<>();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(new User(mobile, "", grantedAuths), "",grantedAuths);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
        
    }

}

























