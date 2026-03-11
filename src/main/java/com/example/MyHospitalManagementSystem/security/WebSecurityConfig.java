package com.example.MyHospitalManagementSystem.security;

import com.example.MyHospitalManagementSystem.enums.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import static com.example.MyHospitalManagementSystem.enums.PermissionType.*;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomeUserDetailsService customeUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JWTAuthFilter jwtAuthFilter;
    private final OAuthSeccessHandler oAuthSeccessHandler;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers(
                                "/public/**",
                                "/auth/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // Allow preflight OPTIONS requests
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Admin DELETE endpoints with authority
                        .requestMatchers(HttpMethod.DELETE, "/admin/**")
                        .hasAnyAuthority(APPOINTMENT_DELETE.name(), USER_MANAGE.name())
                        // Role-based access
                        .requestMatchers("/doctors/**").hasAnyRole(RoleType.DOCTOR.name(), RoleType.ADMIN.name())
                        .requestMatchers("/patients/**").hasAnyRole(RoleType.PATIENT.name(), RoleType.ADMIN.name())
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                .userDetailsService(customeUserDetailsService)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                // OAuth2 login config
                .oauth2Login(oAuth2 -> oAuth2
                        .failureHandler((request, response, exception) -> {
                            log.error("oAuth2 Error: {}", exception.getMessage());
                            handlerExceptionResolver.resolveException(request, response, null, exception);
                        })
                        .successHandler(oAuthSeccessHandler)
                )
                // Exception handling
                .exceptionHandling(ex -> ex.accessDeniedHandler(
                        (request, response, accessDeniedException) -> {
                            handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
                        }
                ));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}