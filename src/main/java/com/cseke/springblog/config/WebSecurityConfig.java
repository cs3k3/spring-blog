package com.cseke.springblog.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers(antMatcher("/register")).permitAll()
                    .requestMatchers(antMatcher("/login")).permitAll()
                    .requestMatchers(antMatcher("/")).permitAll()
                    .requestMatchers(antMatcher("/posts/**")).permitAll()
                    .requestMatchers(antMatcher("/h2-console/**")).permitAll()
                    //.requestMatchers(PathRequest.toH2Console()).permitAll()
                    .anyRequest().authenticated()
                );

        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        http
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error")
                        .permitAll()
                );

        return http.build();
    }
}
