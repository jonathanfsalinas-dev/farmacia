package com.farmacia.security;

// Esta clase configura la seguridad de la aplicación usando Spring Security

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// Habilita la seguridad a nivel de métodos, 
// lo que permite usar anotaciones como @PreAuthorize en los controladores para controlar el acceso a los endpoints 
// según los roles de usuario.
@EnableMethodSecurity

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                // USUARIOS (POST y DELETE solo para ADMIN, GET para ADMIN y OPERADOR)
                .requestMatchers(HttpMethod.POST, "/usuarios").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyRole("ADMIN", "OPERADOR")

                // MOVIMIENTOS (todos autenticados)
                .requestMatchers("/movimientos/**").authenticated()

                // todo lo demás autenticado
                .anyRequest().authenticated()
            )

            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // Configuración del AuthenticationManager para manejar la autenticación de usuarios
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Configuración del PasswordEncoder, 
    // en este caso NoOpPasswordEncoder para no encriptar las contraseñas (solo para desarrollo)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}