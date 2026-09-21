package com.dtp.school_management_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                                .requestMatchers(HttpMethod.POST,"/api/v1/students").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/v1/students").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/v1/students/{studentId}").hasRole("STUDENT")
                                .requestMatchers(HttpMethod.DELETE,"/api/v1/students/{studentId}").hasRole("ADMIN")


                                .requestMatchers(HttpMethod.POST,"/api/v1/teachers").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/v1/teachers").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/v1/teachers/{teacherId}").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.DELETE,"/api/v1/teachers/{teacherId}").hasRole("ADMIN")


                                .requestMatchers(HttpMethod.GET,"/api/v1/courses").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/v1/courses/available").hasRole("STUDENT")
                                .requestMatchers(HttpMethod.GET,"/api/v1/courses/enrolled").hasRole("STUDENT")
                                .requestMatchers(HttpMethod.GET,"/api/v1/courses/taught").hasRole("TEACHER")
                                .requestMatchers(HttpMethod.POST,"/api/v1/courses").hasRole("ADMIN")


                                .requestMatchers(HttpMethod.GET,"/api/v1/enrollments").hasAnyRole("STUDENT", "TEACHER","ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/v1/enrollments/{courseCode}").hasAnyRole( "TEACHER","ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/enrollments/{courseCode}").hasAnyRole("STUDENT","TEACHER","ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/v1/enrollments").hasRole("STUDENT")
                                .requestMatchers(HttpMethod.PUT,"/api/v1/enrollments/{enrollmentId}").hasRole("TEACHER")

                                  .anyRequest().authenticated()
                );

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());


        return http.build();
    }





}
