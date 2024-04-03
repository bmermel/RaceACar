package com.Equipo2.RaceACar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedOrigin("http://localhost:5174");
        config.addAllowedOrigin("http://admin.rentacardh.com");
        config.addAllowedOrigin("http://ec2-44-204-2-67.compute-1.amazonaws.com");
        config.addAllowedOrigin("http://prin.rentacardh.com");
        config.addAllowedOrigin("http://www.rentacardh.com");


        config.setAllowedOrigins(List.of("http://admin.rentacardh.com","http://localhost:5173", "http://localhost:5174", "http://ec2-44-204-2-67.compute-1.amazonaws.com", "http://www.rentacardh.com"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
        WebMvcConfigurer.super.addCorsMappings(registry);
    }

}
