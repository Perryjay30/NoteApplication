//package com.perry.ClassicNotes.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity
//                .cors().and().csrf().disable()
//                .authorizeRequests()
//                // permit ost requests to api/v/user
//                .antMatchers(HttpMethod.POST, "/api/v1/user")
//                .permitAll()
//                // authenticate every other request
//                .anyRequest()
//                .authenticated();
//
//        return httpSecurity.build();
//    }
//}
