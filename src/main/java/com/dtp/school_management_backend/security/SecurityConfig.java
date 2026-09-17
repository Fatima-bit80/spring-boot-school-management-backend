package com.dtp.school_management_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);


        //define query to get user by username
        theUserDetailsManager
                .setUsersByUsernameQuery("select email, password, active from member where email=?"); //regular sql

        //define query to find authorities/roles by username
        theUserDetailsManager
                .setAuthoritiesByUsernameQuery("select email, role from member where email=?"); //regular sql

        return theUserDetailsManager;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/api/admins/**").hasRole("ADMIN")
                                .requestMatchers("/api/teachers/**").hasRole("TEACHER")
                                 .requestMatchers("/api/students/**").hasRole("STUDENT")
                                  .anyRequest().authenticated()
                );

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());


        return http.build();
    }





}
