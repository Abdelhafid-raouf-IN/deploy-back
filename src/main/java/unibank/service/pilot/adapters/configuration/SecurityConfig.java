package unibank.service.pilot.adapters.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.addAllowedOrigin("http://localhost:3000");
                    config.addAllowedMethod("GET");
                    config.addAllowedMethod("POST");
                    config.addAllowedMethod("PUT");
                    config.addAllowedMethod("DELETE");
                    config.addAllowedMethod("PATCH");
                    config.addAllowedHeader("*");
                    config.setAllowCredentials(true);
                    return config;
                }))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/docs/**").permitAll()
                        .requestMatchers("/api/endpoints/**").permitAll()
                        .requestMatchers("/api/tests/**").permitAll()
                        .requestMatchers("/api/token/**").permitAll()
                        .requestMatchers("/api/status/**").permitAll()
                        .requestMatchers("/api/branches/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()  // Permettre l'accès aux points de terminaison Actuator
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
