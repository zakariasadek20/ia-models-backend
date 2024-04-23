package com.models.ai.rest.users.authentication.keycloack;

import com.models.ai.rest.users.adapters.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ConditionalOnProperty(prefix = "service", name = "security.authentication.type", havingValue = "keycloak")
public class AuthenticationKeycloackService {

    public KeyCloackTokenDTO authenticate() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            throw new InsufficientAuthenticationException("");
        }

        Jwt jwt = (Jwt) authentication.getPrincipal();

        KeyCloackTokenDTO keyCloackTokenDTO = KeyCloackTokenDTO.builder()
                .accessToken(jwt.getTokenValue())
                .expiresIn(jwt.getExpiresAt())
                .scope(jwt.getClaim("scope"))
                .sessionState(jwt.getClaim("session_state"))
                .tokenType(jwt.getClaim("typ"))
                .issuedAt(jwt.getIssuedAt())
                .build();
        return keyCloackTokenDTO;
    }
}
