package com.models.ai.infra.config;

import com.models.ai.domain.users.UserId;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class AuditorAwareImpl implements AuditorAware<UserId> {

    // private final SecurityService securityService;

    @Override
    public Optional<UserId> getCurrentAuditor() {
        try {
            // User authenticatedUser = securityService.getAuthenticatedUser();
            return Optional.of(UserId.from(UUID.randomUUID().toString()));
        } catch (Exception e) {
            log.warn("Authenticated User doesn't exists. Fallback to AnonymousUser(-1)");
            return Optional.of(UserId.from("-1"));
        }
    }
}
