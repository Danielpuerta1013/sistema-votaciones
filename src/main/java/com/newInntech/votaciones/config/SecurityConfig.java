package com.newInntech.votaciones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
         return http
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/voter/**", "/api/candidate/**").authenticated()
                        .requestMatchers("/api/votes/**").authenticated()
                        .anyRequest().authenticated()
                ).
                httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable).build();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("admin")
                .password("{noop}1234")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }


}
