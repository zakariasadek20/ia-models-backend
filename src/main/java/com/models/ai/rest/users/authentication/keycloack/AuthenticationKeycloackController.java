package com.models.ai.rest.users.authentication.keycloack;

import com.models.ai.rest.config.security.jwt.JWTFilter;
import com.models.ai.rest.users.adapters.KeyCloackTokenDTO;
import com.models.ai.rest.utils.ApiResponse;
import com.models.ai.rest.utils.ResourcePaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(prefix = "service", name = "security.authentication.type", havingValue = "keycloak")
public class AuthenticationKeycloackController {

    private final AuthenticationKeycloackService authenticationKeycloackService;

    @PostMapping(path = ResourcePaths.Users.ENDPOINT_API_USERS_AUTHENTICATE)
    public ResponseEntity<ApiResponse<KeyCloackTokenDTO>> authenticate() {

        KeyCloackTokenDTO keyCloackTokenDTO = authenticationKeycloackService.authenticate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + keyCloackTokenDTO.getAccessToken());

        return new ResponseEntity<>(ApiResponse.ok(keyCloackTokenDTO), httpHeaders, HttpStatus.OK);
    }
}
