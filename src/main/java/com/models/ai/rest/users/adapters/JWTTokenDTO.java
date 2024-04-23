package com.models.ai.rest.users.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JWTTokenDTO {
    @JsonProperty("token_id")
    private String tokenId;

    @JsonProperty("users_detail")
    private UserDTO userDTO;
}
