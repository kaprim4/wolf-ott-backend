package com.wolfott.mangement.line.configs;

import com.wolfott.mangement.line.filters.AuthenticationFilter;
import com.wolfott.mangement.line.providers.HeaderAuthenticationProvider;
import com.wolfott.mangement.line.providers.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Lazy
    @Autowired
    private AuthenticationFilter authenticationFilter;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.addFilterAfter(authenticationFilter, BasicAuthenticationFilter.class);
        http.authorizeHttpRequests(registry ->{
            registry.requestMatchers("/**").authenticated();
        });
//            .exceptionHandling()
//            .authenticationEntryPoint(new JwtAuthenticationEntryPoint());
        return http.build();
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        return new JwtAuthenticationProvider();
    }

    @Bean
    AuthenticationConverter authenticationConverter(){
        return new JwtAuthenticationProvider();
    }
}
