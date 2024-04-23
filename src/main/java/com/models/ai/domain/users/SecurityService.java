package com.models.ai.domain.users;

import com.models.ai.domain.users.profiles.ProfileId;

public interface SecurityService {
    User getAuthenticatedUser();

    ProfileId getCurrentProfileIdForAuthenticatedUser();
}
