package com.models.ai.rest.users.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.ai.domain.referentiel.entitylabels.EntityLabel;
import com.models.ai.rest.referentiel.entitylabels.LabelDTO;
import java.util.List;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatutDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("labels")
    private Map<String, LabelDTO> labels;

    public static UserStatutDTO from(String statut, List<EntityLabel> labels) {
        if (statut == null) {
            return new UserStatutDTO();
        }
        return builder().id(statut).labels(LabelDTO.toMap(labels)).build();
    }
}
