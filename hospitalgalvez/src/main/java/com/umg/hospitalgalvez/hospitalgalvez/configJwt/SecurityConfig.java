package com.umg.hospitalgalvez.hospitalgalvez.configJwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.umg.hospitalgalvez.hospitalgalvez.Jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // Añadido: Habilitar la seguridad global basada en métodos
public class SecurityConfig {

        // .requestMatchers(HttpMethod.GET).permitAll()

        // .requestMatchers("/usuarios/**").permitAll()
        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final AuthenticationProvider authProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers(HttpMethod.POST).permitAll()
                        .requestMatchers(HttpMethod.DELETE).permitAll()
                        .requestMatchers(HttpMethod.PUT).permitAll()
                        .requestMatchers(HttpMethod.PATCH).permitAll()
                        .requestMatchers(HttpMethod.HEAD).permitAll()
                        .requestMatchers(HttpMethod.TRACE).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .anyRequest().authenticated())
                    .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authProvider)
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
                return http.build();
            }
}
