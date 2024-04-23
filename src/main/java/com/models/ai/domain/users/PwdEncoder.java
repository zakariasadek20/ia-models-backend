package com.models.ai.domain.users;

public interface PwdEncoder {
    String encode(String password);

    boolean matches(String clearPassword, String encodedPassword);
}
