package com.models.ai.rest.users.profiles;

import com.models.ai.domain.users.profiles.Profile;
import com.models.ai.domain.users.profiles.ProfileId;
import com.models.ai.domain.users.profiles.ProfileProvider;
import com.models.ai.domain.users.profiles.ProfileService;
import com.models.ai.domain.users.profiles.commands.ProfileAddCommand;
import com.models.ai.rest.users.profiles.adapters.ProfileAdapter;
import com.models.ai.rest.users.profiles.adapters.ProfileDetailDTO;
import com.models.ai.rest.utils.ApiResponse;
import com.models.ai.rest.utils.ResourcePaths.Profiles;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProfileController {

    private final ProfileProvider profileProvider;
    private final ProfileService profileService;
    private final ProfileAdapter profileAdapter;

    @GetMapping(path = Profiles.ENDPOINT_API_PROFILES)
    public ResponseEntity<ApiResponse<List<ProfileDetailDTO>>> getProfiles() {
        log.info("REST request to get Profiles");

        List<Profile> profiles = profileProvider.getAll();

        List<ProfileDetailDTO> result =
                profiles.stream().map(ProfileDetailDTO::from).collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.ok(result));
    }

    @GetMapping(path = Profiles.ENDPOINT_API_PROFILES_PROFILE_WITH_MENU)
    public ResponseEntity<ApiResponse<ProfileDetailDTO>> getProfileMenu(
            @PathVariable(value = Profiles.PathVariales.PROFILE_ID) String profileId) {
        log.info("REST request to get Profile Menu for <ProfileId>: {}", profileId);

        Profile profile = profileProvider.getById(ProfileId.from(profileId)).orElseThrow();
        ProfileDetailDTO result = profileAdapter.adaptProfileWithAllMenu(profile);

        return ResponseEntity.ok(ApiResponse.ok(result));
    }

    @PostMapping(path = Profiles.ENDPOINT_API_PROFILES)
    public ResponseEntity<ApiResponse<ProfileDetailDTO>> addProfile(
            @Valid @RequestBody ProfileAddCommand profileAddCommand) {
        log.info("REST request to get Profiles");

        Profile profile = profileService.add(profileAddCommand);

        return ResponseEntity.ok(ApiResponse.ok(ProfileDetailDTO.from(profile)));
    }
}
