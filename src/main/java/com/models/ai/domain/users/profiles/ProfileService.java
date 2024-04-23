package com.models.ai.domain.users.profiles;

import com.models.ai.domain.users.menus.menusactions.MenuActionId;
import com.models.ai.domain.users.profiles.commands.ProfileAddCommand;
import com.models.ai.domain.users.profiles.commands.ProfileUpdateCommand;
import com.models.ai.domain.users.profiles.profilesmenusactions.ProfileMenuAction;
import com.models.ai.domain.users.profiles.profilesmenusactions.ProfileMenuActionId;
import com.models.ai.domain.users.profiles.profilesmenusactions.ProfileMenuActionProvider;
import com.models.ai.domain.users.profiles.profilesmenusactions.commands.ProfileMenuActionAssignCommand;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

    private final ProfileProvider profileProvider;
    private final ProfileMenuActionProvider profileMenuActionProvider;

    public Profile add(ProfileAddCommand profileAddCommand) {
        Profile profile = Profile.from(profileAddCommand);

        return profileProvider.save(profile);
    }

    public Profile update(ProfileId profileId, ProfileUpdateCommand profileUpdateCommand) {

        Profile profile = profileProvider.getById(profileId).orElseThrow();

        profile.update(profileUpdateCommand);

        return profileProvider.save(profile);
    }

    public List<ProfileMenuAction> assignActions(ProfileMenuActionAssignCommand command) {
        Set<MenuActionId> actionIdsToSet =
                Optional.ofNullable(command.getActions()).orElse(Collections.emptySet()).stream()
                        .map(MenuActionId::from)
                        .collect(Collectors.toSet());

        ProfileId profileId = ProfileId.from(command.getProfileId());

        profileMenuActionProvider.removeAllActionsForProfile(profileId);

        List<ProfileMenuAction> profileMenuActionIds = actionIdsToSet.stream()
                .map(a -> new ProfileMenuActionId(profileId, a))
                .map(ProfileMenuAction::new)
                .collect(Collectors.toList());

        return profileMenuActionProvider.save(profileMenuActionIds);
    }
}
