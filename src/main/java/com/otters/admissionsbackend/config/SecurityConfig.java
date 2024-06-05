package com.otters.admissionsbackend.config;

import com.otters.admissionsbackend.filter.JwtAuthenticationFilter;
import com.otters.admissionsbackend.model.Role;
import com.otters.admissionsbackend.service.UserDetailsServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsServiceImp userDetailsServiceImp;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsServiceImp userDetailsServiceImp, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req.requestMatchers("/login/**", "/register/**")
                                .permitAll()
                                .requestMatchers("/profile/**").authenticated()
                                .requestMatchers("/subject/**").authenticated()
                                .requestMatchers("/exam/**").authenticated()
                                .requestMatchers("/major_class/**").authenticated()
                                .requestMatchers("/bench_mark/**").authenticated()
                                .requestMatchers("/room/**").authenticated()
                                .requestMatchers("/student/**").authenticated()
                                .requestMatchers("/paper/**").authenticated()
                                .requestMatchers("/registrations/**").authenticated()
                                .requestMatchers("/examRoomDetails/**").authenticated()
                                .requestMatchers("/subjectSets/**").authenticated()
                                .requestMatchers("/stRegistrationDetails/**").authenticated()
                                .requestMatchers(HttpMethod.GET, "/news/**").permitAll()
                                .requestMatchers("/admin/news/**").hasAnyAuthority(String.valueOf(Role.ADMIN), String.valueOf(Role.KHAOTHI), String.valueOf(Role.DAOTAO))
                                .requestMatchers("/admin/user").hasAnyAuthority(String.valueOf(Role.ADMIN))
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(userDetailsServiceImp)
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
