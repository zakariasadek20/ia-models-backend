package com.models.ai.rest.users.profilesmenusactions;

import com.models.ai.domain.users.profiles.ProfileService;
import com.models.ai.domain.users.profiles.profilesmenusactions.commands.ProfileMenuActionAssignCommand;
import com.models.ai.rest.utils.ApiResponse;
import com.models.ai.rest.utils.ResourcePaths.ProfilesMenusActions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProfileMenuActionController {

    private final ProfileService profileService;

    @PostMapping(path = ProfilesMenusActions.ENDPOINT_API_PROFILES_MENUS_ACTIONS)
    public ResponseEntity<ApiResponse<Object>> assignActionsToProfile(
            @Validated @RequestBody ProfileMenuActionAssignCommand command) {
        log.info("REST request to assign Actions to a Profile <{}> ", command.getProfileId());

        profileService.assignActions(command);

        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
