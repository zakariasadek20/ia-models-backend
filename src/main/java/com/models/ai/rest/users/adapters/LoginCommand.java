package com.models.ai.rest.users.adapters;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCommand {
    @NotNull
    private String username;

    @NotNull
    private String password;

    private Boolean rememberMe;
}
