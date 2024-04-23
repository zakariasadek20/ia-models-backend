package com.models.ai.domain.users.profiles.profilesmenusactions;

import com.models.ai.domain.users.profiles.ProfileId;
import java.util.List;

public interface ProfileMenuActionProvider {

    List<ProfileMenuAction> getBy(ProfileId profileId);

    void removeAllActionsForProfile(ProfileId profileId);

    List<ProfileMenuAction> save(List<ProfileMenuAction> profileMenuActionIds);
}
