package com.models.ai.infra.users.usersprofiles;

import com.models.ai.domain.users.UserId;
import com.models.ai.domain.users.profiles.Profile;
import com.models.ai.domain.users.profiles.ProfileId;
import com.models.ai.domain.users.usersprofiles.UserProfile;
import com.models.ai.domain.users.usersprofiles.UserProfileId;
import com.models.ai.domain.users.usersprofiles.UserProfileProvider;
import com.models.ai.infra.users.profiles.ProfileRepository;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserProfileProviderImpl implements UserProfileProvider {

    public static final String USER_PROFILES = "USER_PROFILES";

    private final UserProfileRepository userProfileRepository;
    private final ProfileRepository profileRepository;

    @Override
    public Optional<UserProfile> findById(UserProfileId userProfileId) {
        return userProfileRepository.findById(userProfileId);
    }

    @Override
    @Cacheable(cacheNames = USER_PROFILES)
    public List<Profile> getBy(UserId userId) {
        List<UserProfile> userProfiles = userProfileRepository.findById_userId(userId);

        return profileRepository.findAllById(
                userProfiles.stream().map(up -> up.getId().getProfileId()).collect(Collectors.toSet()));
    }

    @Override
    @CacheEvict(cacheNames = USER_PROFILES, allEntries = true)
    @Transactional
    public UserProfile add(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    @CacheEvict(cacheNames = USER_PROFILES)
    @Transactional
    public void deleteUserProfile(UserId userId) {
        userProfileRepository.deleteBy_UserId(userId);
        userProfileRepository.flush();
    }

    @Override
    public Map<UserId, List<Profile>> getProfilesByUserIds(Set<UserId> userIds) {
        List<UserProfile> userProfiles = new ArrayList<>();
        List<List<UserId>> partitions = ListUtils.partition(new ArrayList<>(userIds), 999);
        for (List<UserId> partition : partitions) {
            userProfiles.addAll(userProfileRepository.findById_userIdIn(partition));
        }

        List<ProfileId> distinctProfiles = userProfiles.stream()
                .map(up -> up.getId().getProfileId())
                .distinct()
                .collect(Collectors.toList());

        List<Profile> profiles = profileRepository.findAllById(distinctProfiles);

        Map<UserId, List<UserProfile>> userProfileIdsMap = userProfiles.stream()
                .collect(Collectors.groupingBy(o -> o.getId().getUserId()));

        Map<UserId, List<Profile>> userProfilesMap = new HashMap<>();

        userIds.forEach(u -> {
            List<ProfileId> oneUserProfiles = userProfileIdsMap.get(u).stream()
                    .map(p -> p.getId().getProfileId())
                    .collect(Collectors.toList());

            userProfilesMap.put(
                    u,
                    profiles.stream()
                            .filter(p -> oneUserProfiles.contains(p.getId()))
                            .collect(Collectors.toList()));
        });

        return userProfilesMap;
    }
}
