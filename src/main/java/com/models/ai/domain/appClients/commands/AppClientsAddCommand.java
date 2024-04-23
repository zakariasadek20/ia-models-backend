package com.models.ai.domain.appClients.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppClientsAddCommand {
    @NotNull
    @JsonProperty(value = "name")
    private String name;

    @NotNull
    @JsonProperty(value = "host")
    private String host;

    @NotNull
    @JsonProperty(value = "store")
    private String store;
}
