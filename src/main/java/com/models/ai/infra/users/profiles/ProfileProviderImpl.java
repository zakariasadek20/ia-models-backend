package com.models.ai.infra.users.profiles;

import com.models.ai.domain.users.profiles.Profile;
import com.models.ai.domain.users.profiles.ProfileId;
import com.models.ai.domain.users.profiles.ProfileProvider;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@RequiredArgsConstructor
public class ProfileProviderImpl implements ProfileProvider {

    private static final String PROFILE_CACHE = "PROFILE_CACHE";

    private final ProfileRepository profileRepository;

    @Override
    @CacheEvict(value = PROFILE_CACHE, allEntries = true)
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Optional<Profile> getById(ProfileId profileId) {
        return profileRepository.findById(profileId);
    }

    @Override
    @Cacheable(PROFILE_CACHE)
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> getByCode(String profileCode) {
        return profileRepository.getByCode(profileCode);
    }
}
