package com.models.ai.rest.config.security;

import com.models.ai.domain.users.User;
import com.models.ai.domain.users.UserProvider;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Authenticate a user from the database. */
@Component("userDetailsService")
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(prefix = "service", name = "security.authentication.type", havingValue = "jwt")
public class DomainUserDetailsService implements UserDetailsService {
    private final UserProvider userProvider;

    @Override
    @Transactional
    public CustomUser loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        // if (new EmailValidator().isValid(login, null)) {
        // return userRepository.findOneWithAuthoritiesByEmailIgnoreCase(login)
        // .map(user -> createSpringSecurityUser(login, user))
        // .orElseThrow(() -> new UsernameNotFoundException("User with email " +
        // login + " was not found in the database"));
        // }

        String lowercaseLogin = login.toLowerCase();
        return userProvider
                .getByLogin(lowercaseLogin)
                .map(user -> createSpringSecurityUser(lowercaseLogin, user))
                .orElseThrow(() ->
                        new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private CustomUser createSpringSecurityUser(String lowercaseLogin, User user) {
        // if (!user.getActivated()) {
        // throw new UserNotActivatedException("User " + lowercaseLogin + " was not
        // activated");
        // }
        // List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
        // .map(authority -> new SimpleGrantedAuthority(authority.getName()))
        // .collect(Collectors.toList());

        return new CustomUser(user.getLogin(), user.getPassword(), Collections.emptyList(), user);
    }
}
