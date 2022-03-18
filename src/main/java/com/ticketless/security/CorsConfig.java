package com.ticketless.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // TODO Auto-generated method stub
        super.addCorsMappings(registry);
        registry.addMapping("/**")
        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
        .allowedOrigins("http://localhost:3000","http://localhost:3007","http://192.168.29.145:3000","http://192.168.0.109:3000","http://lets-chat.s3-website.ap-south-1.amazonaws.com");
    }
}
