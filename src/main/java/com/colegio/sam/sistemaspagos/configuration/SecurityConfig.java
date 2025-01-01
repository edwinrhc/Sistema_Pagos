package com.colegio.sam.sistemaspagos.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     return http
             .csrf(csrf -> csrf.disable())
             .authorizeHttpRequests(auth -> auth
                     .requestMatchers("/public/**").permitAll()
                     .requestMatchers("/v1/home").authenticated()
                     .requestMatchers("/v1/admin").hasAuthority("ADMIN")
                     .anyRequest().authenticated()
             )
             .formLogin(form -> form
                     .loginPage("/login")
                     .defaultSuccessUrl("/v1/home",true)
                     .permitAll()
             )
             .build();


    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encriptación de contraseñas.
    }

}
