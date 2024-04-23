package com.models.ai.rest.users;

import com.models.ai.domain.users.Commands.SignUpCommand;
import com.models.ai.domain.users.SignUpService;
import com.models.ai.domain.users.User;
import com.models.ai.rest.utils.ApiResponse;
import com.models.ai.rest.utils.ResourcePaths;
import com.models.ai.rest.utils.Utils;
import jakarta.validation.Valid;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(prefix = "service", name = "security.authentication.type", havingValue = "jwt")
public class SignUpController {
    private final SignUpService signupService;

    @PostMapping(path = ResourcePaths.SignUp.ENDPOINT_API_SIGNUP)
    public ResponseEntity<ApiResponse<?>> signUp(@Valid @RequestBody SignUpCommand command) throws URISyntaxException {
        log.info("REST request to signup : {}", command);

        User newUser = signupService.signUp(command);

        return ResponseEntity.created(Utils.constructUriWithParams(
                        ResourcePaths.Users.ENDPOINT_API_USERS_ONE_USER,
                        newUser.getId().getValue()))
                .body(ApiResponse.ok(null));
    }
}
