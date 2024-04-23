package com.models.ai.domain.users.usersprofiles;

import com.models.ai.domain.users.UserId;
import com.models.ai.domain.users.profiles.Profile;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface UserProfileProvider {
    Optional<UserProfile> findById(UserProfileId userProfileId);

    List<Profile> getBy(UserId userId);

    Map<UserId, List<Profile>> getProfilesByUserIds(Set<UserId> userIds);

    UserProfile add(UserProfile userProfile);

    void deleteUserProfile(UserId userId);
}
