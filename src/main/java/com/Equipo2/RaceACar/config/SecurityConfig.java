package com.Equipo2.RaceACar.config;

import com.Equipo2.RaceACar.JWT.JwtAuthenticationFilter;
import com.Equipo2.RaceACar.User.Roles;
import com.Equipo2.RaceACar.model.Enums.Permisos;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.hibernate.cfg.AvailableSettings.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity


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
                        .requestMatchers("/admin/**").permitAll()
                                .anyRequest().authenticated()



                        //.requestMatchers("/usuarios/**").hasAnyAuthority("user:read","user:create")
                        //.requestMatchers(HttpMethod.GET,"/usuarios/**").hasAnyAuthority("user:read","user:write")
                        //.requestMatchers(HttpMethod.GET,"/usuarios/**").hasRole("USER")
                        //.requestMatchers(HttpMethod.GET,"/usuarios/**").hasAnyAuthority(Permisos.USER_READ.name(),Permisos.USER_CREATE.name(),Permisos.USER_DELETE.name(),Permisos.USER_PATCH.name(),Permisos.USER_UPDATE.name())
                        //.requestMatchers("/reservas/**").hasAnyAuthority("user:update", "user:read", "user:create", "user:delete", "user:patch")
                                //.anyRequest().authenticated()
                                //.hasAnyAuthority("user:update", "user:read", "user:create", "user:delete", "user:patch", "ROLE_USER", "ROLE_ADMIN", "ROLE_SUPER_ADMIN")




                )                .sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider((authProvider))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class )
                .build();
    }
}
