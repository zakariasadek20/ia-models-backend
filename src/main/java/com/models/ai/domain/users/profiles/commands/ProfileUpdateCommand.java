package com.models.ai.domain.users.profiles.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileUpdateCommand {
    @NotNull
    @JsonProperty(value = "id")
    private String id;

    @NotNull
    @JsonProperty(value = "code")
    private String code;

    @NotNull
    @JsonProperty(value = "description")
    private String description;

    @NotNull
    @JsonProperty(value = "niveau")
    private Long niveau;

    @NotNull
    @JsonProperty(value = "type")
    private String type;
}
