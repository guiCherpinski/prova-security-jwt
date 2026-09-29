package br.prova.jwt.provajwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Essa é classe de configuração de segurança
 */

@Configuration
public class SecurityConfig {

    private SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter){
        this.securityFilter = securityFilter;
    }

    /**
     * Método que aplica a segurança geral nas urls e métodos
     * @param security
     * @return SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity security){
        return security
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sh -> sh.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/swagger-ui.html","/h2-console").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/chamados").hasAllAuthorities("CLIENTE","TECNICO","ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/v1/chamados").hasAllAuthorities("CLIENTE","TECNICO","ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/chamados/**").hasAnyRole("TECNICO","ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/chamados/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return new AuthenticationConfiguration().getAuthenticationManager();
    }
}
