package com.models.ai.domain.users.profiles.profilesmenusactions.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileMenuActionAssignCommand {

    @JsonProperty(value = "profile_id")
    private String profileId;

    @JsonProperty(value = "actions")
    private Set<String> actions;
}
