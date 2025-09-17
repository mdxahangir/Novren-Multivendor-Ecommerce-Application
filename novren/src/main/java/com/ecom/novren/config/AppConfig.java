//package com.ecom.novren.config;
//
//
//import jakarta.servlet.http.HttpServlet;
//import org.apache.tomcat.util.file.ConfigurationSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.io.IOException;
//import java.net.URI;
//import java.util.Collections;
//
//@Configuration
//@EnableWebSecurity
//public class AppConfig {
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.sessionManagement(management->management.sessionCreationPolicy(
//                SessionCreationPolicy.STATELESS)).authorizeRequests(authorize->authorize
//                .requestMatchers("/api/**").authenticated()
//                .requestMatchers("/api/products/*/reviews").permitAll()
//                .anyRequest().permitAll()
//        ).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
//                .csrf(csrf-> csrf.disable())
//                .csrs(cors-> cors.ConfigurationSource(corsConfigrationSorure()));
//                return http.build();
//    }
//
//    private ConfigurationSource corsConfigrationSorure() {
//        return new ConfigurationSource() {
//            @Override
//            public Configuration getConfiguration(HttpServlet request) throws IOException {
//                CorsConfiguration cfg = new CorsConfiguration();
//                cfg.setAllowedOrigins(Collections.singletonList("*"));
//                cfg.setAllowedMethods(Collections.singletonList("*"));
//                cfg.setAllowedHeaders(Collections.singletonList("*"));
//                cfg.setAllowCredentials(true);
//                cfg.setExposedHeaders(Collections.singletonList("Authorization"));
//                cfg.setMaxAge(3600l);
//                return cfg;
//            }
//
//        };
////        return null;
//    }
//
//}
package com.ecom.novren.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(management ->
                        management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/api/products/*/reviews").permitAll()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource())); // ✅ ঠিক করা হলো

        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration cfg = new CorsConfiguration();
            cfg.setAllowedOrigins(Collections.singletonList("*"));
            cfg.setAllowedMethods(Collections.singletonList("*"));
            cfg.setAllowedHeaders(Collections.singletonList("*"));
            cfg.setAllowCredentials(true);
            cfg.setExposedHeaders(Collections.singletonList("Authorization"));
            cfg.setMaxAge(3600L);
            return cfg;
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
