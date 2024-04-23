package com.models.ai.domain.users.profiles;

import java.util.List;
import java.util.Optional;

public interface ProfileProvider {
    Profile save(Profile profile);

    Optional<Profile> getById(ProfileId profileId);

    List<Profile> getAll();

    Optional<Profile> getByCode(String profileCode);
}
