package com.example.todos.app.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(
            @Value("${API_LOGIN}") String username,
            @Value("${API_PASSWORD}") String password
    ) {

        UserDetails userDetails1 = createNewUser(username, password);
        return new InMemoryUserDetailsManager(userDetails1);
    }

    private UserDetails createNewUser(String username, String password) {

        UserDetails userDetails = User.builder()
                .passwordEncoder(passwordEncoder::encode)
                .username(username)
                .password(password)
                .roles("USER","ADMIN")
                .build();
        return userDetails;
    }

    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/success").permitAll()
                        .anyRequest().authenticated());
        http.formLogin(withDefaults());

        http.csrf(csrf -> csrf.disable());
        // OR
        // http.csrf(AbstractHttpConfigurer::disable);
        // Starting from SB 3.1.x
        http.headers(
                headers -> headers.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::disable
                )
        );

        return http.build();
    }
}
