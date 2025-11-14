package ro.andreialionte.magazinonline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())            // disable CSRF if your endpoints are stateless REST
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()             // allow every request without authentication
                )
                .httpBasic(httpBasic -> httpBasic.disable())   // disable HTTP Basic
                .formLogin(formLogin -> formLogin.disable());  // disable form login

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new Argon2PasswordEncoder(16, 32, 2, 65536, 3); //65536 = 64mb
    }
}
