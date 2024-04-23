package com.models.ai.infra.users.profilesmenusactions;

import com.models.ai.domain.users.profiles.ProfileId;
import com.models.ai.domain.users.profiles.profilesmenusactions.ProfileMenuAction;
import com.models.ai.domain.users.profiles.profilesmenusactions.ProfileMenuActionProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class ProfileMenuActionProviderImpl implements ProfileMenuActionProvider {

    private static final String PROFILE_MENU_ACTION_CACHE = "PROFILE_MENU_ACTION_CACHE";

    private final ProfileMenuActionRepository profileMenuActionRepository;

    @Override
    @Cacheable(value = PROFILE_MENU_ACTION_CACHE)
    public List<ProfileMenuAction> getBy(ProfileId profileId) {
        return profileMenuActionRepository.findById_profileId(profileId);
    }

    @Override
    @CacheEvict(value = PROFILE_MENU_ACTION_CACHE, allEntries = true)
    public void removeAllActionsForProfile(ProfileId profileId) {
        profileMenuActionRepository.deleteById_profileId(profileId);
        profileMenuActionRepository.flush();
    }

    @Override
    @CacheEvict(value = PROFILE_MENU_ACTION_CACHE, allEntries = true)
    public List<ProfileMenuAction> save(List<ProfileMenuAction> profileMenuActions) {
        return profileMenuActionRepository.saveAll(profileMenuActions);
    }
}
