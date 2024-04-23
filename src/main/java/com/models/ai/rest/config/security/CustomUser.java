package com.models.ai.rest.config.security;

import java.util.Collection;
import lombok.Getter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
@ConditionalOnProperty(prefix = "service", name = "security.authentication.type", havingValue = "jwt")
public class CustomUser extends User {

    private final com.models.ai.domain.users.User user;

    public CustomUser(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            com.models.ai.domain.users.User user) {

        super(username, password, authorities);

        this.user = user;
    }
}
