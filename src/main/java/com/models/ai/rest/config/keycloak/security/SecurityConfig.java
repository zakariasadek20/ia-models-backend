package com.models.ai.rest.config.keycloak.security;

import com.models.ai.rest.utils.ResourcePaths;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "service", name = "security.authentication.type", havingValue = "keycloak")
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkUl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.cors(Customizer.withDefaults())
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                .authorizeHttpRequests(request -> request.requestMatchers(
                                HttpMethod.GET, ResourcePaths.StaticData.ENDPOINT_API_STATIC_DATA)
                        .permitAll()
                        //                        .requestMatchers(HttpMethod.POST,
                        // ResourcePaths.Users.ENDPOINT_API_USERS_AUTHENTICATE)
                        //                        .permitAll()
                        .requestMatchers(HttpMethod.POST, ResourcePaths.SignUp.ENDPOINT_API_SIGNUP)
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, ResourcePaths.Otp.ENDPOINT_API_OTP_VALIDATE)
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, ResourcePaths.Otp.ENDPOINT_API_OTP_REGENERATE)
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, ResourcePaths.Passwords.ENDPOINT_API_PASSWORDS_FORGOT)
                        .permitAll()
                        .requestMatchers("/**")
                        .permitAll()
                        .anyRequest()
                        .permitAll())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(
                        jwtConfigurer -> jwtConfigurer.jwkSetUri(jwkUl).jwtAuthenticationConverter(jwtAuthConverter)))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    //    @Bean
    //    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    //        http.authorizeHttpRequests(auth -> auth
    //                .requestMatchers(
    //                                HttpMethod.GET, ResourcePaths.StaticData.ENDPOINT_API_STATIC_DATA)
    //                        .permitAll()
    //                        .requestMatchers(HttpMethod.POST, ResourcePaths.Users.ENDPOINT_API_USERS_AUTHENTICATE)
    //                        .permitAll()
    //                        .requestMatchers(HttpMethod.POST, ResourcePaths.SignUp.ENDPOINT_API_SIGNUP)
    //                        .permitAll()
    //                        .requestMatchers(HttpMethod.POST, ResourcePaths.Otp.ENDPOINT_API_OTP_VALIDATE)
    //                        .permitAll()
    //                        .requestMatchers(HttpMethod.POST, ResourcePaths.Otp.ENDPOINT_API_OTP_REGENERATE)
    //                        .permitAll()
    //                        .requestMatchers(HttpMethod.POST, ResourcePaths.Passwords.ENDPOINT_API_PASSWORDS_FORGOT)
    //                        .permitAll()
    //                .requestMatchers(new AntPathRequestMatcher("/customers*", HttpMethod.OPTIONS.name()))
    //                .permitAll()
    //                .requestMatchers(new AntPathRequestMatcher("/customers*"))
    //                .hasRole("user")
    //                .requestMatchers(new AntPathRequestMatcher("/"))
    //                .permitAll()
    //                .anyRequest()
    //                .authenticated());
    //        http.oauth2ResourceServer((oauth2) -> oauth2
    //                .jwt(Customizer.withDefaults()));
    //        http.oauth2Login(Customizer.withDefaults())
    ////                .logout(logout -> logout.addLogoutHandler(keycloakLogoutHandler).logoutSuccessUrl("/"))
    //                ;
    //        return http.build();
    //
    //    }
}
