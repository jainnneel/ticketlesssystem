package com.ticketless.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.google.common.collect.ImmutableList;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private Jwtfilter filter;
    
    @Autowired
    private CustomAuthenticationprovider customAuthenticationprovider;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationprovider);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // super.configure(http);
        http
        .httpBasic().disable()
        .cors()
        .and()
        .addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class)
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
        .authorizeRequests()
        .antMatchers("/auth/**").permitAll()
        .antMatchers("/place/**").permitAll()
        .antMatchers("/check/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf().disable()
        .formLogin()
        .failureUrl("/auth/authfail")
        .loginPage("/auth/authreq")  
        .loginProcessingUrl("/loginrequest").permitAll()
        .defaultSuccessUrl("/auth/successlogin").permitAll();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD","GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
