package com.models.ai.rest.config.security;

import static org.springframework.security.config.Customizer.withDefaults;

import com.models.ai.rest.config.security.jwt.JWTFilter;
import com.models.ai.rest.utils.ResourcePaths;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "service", name = "security.authentication.type", havingValue = "jwt")
public class ApplicationSecurity {

    private final Environment env;
    private final JWTFilter customFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http.cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(
                        headers -> headers.contentSecurityPolicy(
                                        csp -> csp.policyDirectives(
                                                "default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:"))
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                                .referrerPolicy(referrer -> referrer.policy(
                                        ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN))
                        //                                .permissionsPolicy(permissions ->
                        //                                        permissions.policy(
                        //                                                "camera=(), fullscreen=(self), geolocation=(),
                        // gyroscope=(), magnetometer=(), microphone=(), midi=(), payment=(), sync-xhr=()"
                        //                                        )
                        //                                )
                        )
                .authorizeHttpRequests(request -> request.requestMatchers(
                                HttpMethod.GET, ResourcePaths.StaticData.ENDPOINT_API_STATIC_DATA)
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, ResourcePaths.Users.ENDPOINT_API_USERS_AUTHENTICATE)
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, ResourcePaths.SignUp.ENDPOINT_API_SIGNUP)
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, ResourcePaths.Otp.ENDPOINT_API_OTP_VALIDATE)
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, ResourcePaths.Otp.ENDPOINT_API_OTP_REGENERATE)
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, ResourcePaths.Passwords.ENDPOINT_API_PASSWORDS_FORGOT)
                        .permitAll()
                        .requestMatchers("/**")
                        .authenticated()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }
}
