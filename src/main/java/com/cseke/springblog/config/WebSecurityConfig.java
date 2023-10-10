package com.cseke.springblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {
    private static final String[] WHITELIST = {
            "/register",
            "/login",
            "/h2-console/*",
            "/"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(WHITELIST).permitAll()
                //.requestMatchers(HttpMethod.GET,"/posts/*").permitAll()
                .anyRequest().authenticated();

        /*http.csrf().disable();
        http.headers().frameOptions().disable();*/


        return http.build();
    }
}