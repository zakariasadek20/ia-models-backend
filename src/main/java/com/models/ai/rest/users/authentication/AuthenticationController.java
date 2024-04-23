package com.models.ai.rest.users.authentication;

import com.models.ai.rest.config.security.jwt.JWTFilter;
import com.models.ai.rest.users.adapters.JWTTokenDTO;
import com.models.ai.rest.users.adapters.LoginCommand;
import com.models.ai.rest.utils.ApiResponse;
import com.models.ai.rest.utils.ResourcePaths;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(prefix = "service", name = "security.authentication.type", havingValue = "jwt")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = ResourcePaths.Users.ENDPOINT_API_USERS_AUTHENTICATE)
    public ResponseEntity<ApiResponse<JWTTokenDTO>> authenticate(@Valid @RequestBody LoginCommand loginCommand) {

        JWTTokenDTO jwtTokenDTO = authenticationService.authenticate(loginCommand);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwtTokenDTO.getTokenId());

        return new ResponseEntity<>(ApiResponse.ok(jwtTokenDTO), httpHeaders, HttpStatus.OK);
    }
}
