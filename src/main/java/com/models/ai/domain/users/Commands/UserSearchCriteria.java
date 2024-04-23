package com.models.ai.domain.users.Commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.ai.domain.common.SearchMetaCriteria;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchCriteria {
    @JsonProperty("search_meta_data")
    SearchMetaCriteria searchMetaCriteria;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("login")
    private String login;

    @JsonProperty("email")
    private String email;

    @JsonProperty("profile")
    private String profile;

    @JsonProperty("statut")
    private String statut;

    @JsonProperty("telephone")
    private String telephone;
}
