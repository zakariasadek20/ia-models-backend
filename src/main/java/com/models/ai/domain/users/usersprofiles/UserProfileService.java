package com.models.ai.domain.users.usersprofiles;

import com.models.ai.domain.users.User;
import com.models.ai.domain.users.profiles.Profile;
import com.models.ai.domain.users.profiles.ProfileId;
import com.models.ai.domain.users.profiles.ProfileProvider;
import com.models.ai.domain.users.usersprofiles.exceptions.ProfileAlreadyAssignedToUserException;
import com.models.ai.domain.users.usersprofiles.exceptions.ProfileNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileProvider userProfileProvider;
    private final ProfileProvider profileProvider;

    public void assignProfileToUser(User user, String profileCode) {

        Profile profile = profileProvider.getByCode(profileCode).orElseThrow(ProfileNotFoundException::new);

        UserProfileId userProfileId = new UserProfileId(user.getId(), profile.getId());
        verifyUserProfileDoesNotExist(userProfileId);

        verifyProfileExists(profile.getId());
        verifyUserProfileDoesNotExist(userProfileId);

        UserProfile userProfile = new UserProfile(userProfileId);

        userProfileProvider.add(userProfile);
    }

    public void assignProfileToUser(User user, ProfileId profileId) {
        UserProfileId userProfileId = new UserProfileId(user.getId(), profileId);

        verifyProfileExists(profileId);

        userProfileProvider.deleteUserProfile(user.getId());

        UserProfile userProfile = new UserProfile(userProfileId);

        userProfileProvider.add(userProfile);
    }

    private void verifyProfileExists(ProfileId profileId) {
        Optional<Profile> optionalProfile = profileProvider.getById(profileId);

        if (optionalProfile.isEmpty()) {
            throw new ProfileNotFoundException();
        }
    }

    private void verifyUserProfileDoesNotExist(UserProfileId userProfileId) {
        Optional<UserProfile> optionalUserProfile = userProfileProvider.findById(userProfileId);
        optionalUserProfile.ifPresent(e -> {
            throw new ProfileAlreadyAssignedToUserException();
        });
    }
}
