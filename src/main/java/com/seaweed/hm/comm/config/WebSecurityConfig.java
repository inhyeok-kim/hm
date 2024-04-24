package com.seaweed.hm.comm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf((config)->config.disable())
            .headers((config)->config.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))
            .authorizeHttpRequests(authorizationRequest ->
                authorizationRequest
                        .anyRequest().permitAll()
            )
        ;



        return http.build();
    }
}
