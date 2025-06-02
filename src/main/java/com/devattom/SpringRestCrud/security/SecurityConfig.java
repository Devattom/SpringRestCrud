package com.devattom.SpringRestCrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id, pw, active FROM members WHERE user_id=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id=?");

        return jdbcUserDetailsManager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/students").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET, "/api/students/**").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST, "/api/students").hasRole("MENTOR")
                        .requestMatchers(HttpMethod.PUT, "/api/students").hasRole("MENTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/students/**").hasRole("ADMIN")
        );

        http.httpBasic(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    //    @Bean
//    public InMemoryUserDetailsManager userDetailsManager()
//    {
//        UserDetails toto = User.builder()
//                .username("toto")
//                .password("{noop}Test123.")
//                .roles("STUDENT")
//                .build();
//
//        UserDetails luz = User.builder()
//                .username("luz")
//                .password("{noop}Test123.")
//                .roles("STUDENT", "MENTOR")
//                .build();
//
//        UserDetails david = User.builder()
//                .username("david")
//                .password("{noop}Test123.")
//                .roles("STUDENT", "MENTOR", "ADMIN")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(toto, luz, david);
//    }
}
