package com.Equipo2.RaceACar.config;

import com.Equipo2.RaceACar.JWT.JwtAuthenticationFilter;
import com.Equipo2.RaceACar.User.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired

    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf->
                        csrf
                        .disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/usuarios/**").hasAnyAuthority("user:update", "user:read", "user:create", "user:delete", "user:patch", "ROLE_USER", "ROLE_ADMIN", "ROLE_SUPER_ADMIN")

                        .anyRequest().permitAll()


                )                .sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider((authProvider))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class )
                .build();
    }
}
