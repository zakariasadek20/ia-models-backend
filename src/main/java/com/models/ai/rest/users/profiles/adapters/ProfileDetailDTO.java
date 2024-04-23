package com.models.ai.rest.users.profiles.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.ai.domain.users.profiles.Profile;
import com.models.ai.rest.users.menus.adapters.MenuActionDTO;
import com.models.ai.rest.users.menus.adapters.MenuDetailDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileDetailDTO {

    private String id;
    private String code;
    private String description;
    private Long niveau;

    private List<MenuDetailDTO> menus = new ArrayList<>();

    @JsonProperty("menu_actions")
    private Map<String, List<MenuActionDTO>> menuActions = new HashMap<>();

    public static ProfileDetailDTO from(Profile profile) {
        return ProfileDetailDTO.builder()
                .code(profile.getCode())
                .description(profile.getDescription())
                .id(profile.getId().getValue())
                .niveau(profile.getNiveau())
                .build();
    }

    public static ProfileDetailDTO from(Profile profile, List<MenuDetailDTO> menuDetailDTOS) {
        ProfileDetailDTO profileDetailDTO = from(profile);
        profileDetailDTO.menus = new ArrayList<>(menuDetailDTOS);
        return profileDetailDTO;
    }

    public static ProfileDetailDTO from(
            Profile profile, List<MenuDetailDTO> menuDetailDTOS, Map<String, List<MenuActionDTO>> menuActions) {
        ProfileDetailDTO profileDetailDTO = from(profile);
        profileDetailDTO.menus = new ArrayList<>(menuDetailDTOS);
        profileDetailDTO.menuActions = menuActions;
        return profileDetailDTO;
    }
}
