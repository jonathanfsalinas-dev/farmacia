package com.farmacia.security;
// Esta clase configura la seguridad de la aplicación usando Spring Security
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        
        .authorizeHttpRequests(auth -> auth
            // solo ADMIN puede crear/eliminar
            .requestMatchers(HttpMethod.POST, "/usuarios").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")

            // ADMIN y OPERADOR pueden ver
            .requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyRole("ADMIN", "OPERADOR")

            // todo lo demás autenticado
            .anyRequest().authenticated()
        )

        .httpBasic(Customizer.withDefaults());

    return http.build();
    }

    // 🔥 1. Usa tu servicio de usuarios automáticamente
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 🔥 2. Password en texto plano (por ahora)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}