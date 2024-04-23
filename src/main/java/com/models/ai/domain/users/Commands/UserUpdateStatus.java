package com.models.ai.domain.users.Commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateStatus {

    @JsonProperty("statut")
    private String statut;
}
