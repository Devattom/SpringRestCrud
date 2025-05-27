package com.devattom.SpringRestCrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager()
    {
        UserDetails toto = User.builder()
                .username("toto")
                .password("{noop}Test123.")
                .roles("STUDENT")
                .build();

        UserDetails luz = User.builder()
                .username("luz")
                .password("{noop}Test123.")
                .roles("STUDENT", "MENTOR")
                .build();

        UserDetails david = User.builder()
                .username("david")
                .password("{noop}Test123.")
                .roles("STUDENT", "MENTOR", "ADMIN")
                .build();


        return new InMemoryUserDetailsManager(toto, luz, david);
    }
}
