package com.models.ai.rest.referentiel.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.ai.domain.referentiel.entitylabels.EntityLabel;
import com.models.ai.rest.referentiel.entitylabels.LabelDTO;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdLabelDTO {
    @JsonProperty("id")
    private String id;

    @JsonProperty("labels")
    private Map<String, LabelDTO> labels;

    public static IdLabelDTO from(String id, List<EntityLabel> labels) {
        IdLabelDTO dto = new IdLabelDTO();
        dto.id = id;
        dto.labels = LabelDTO.toMap(labels);
        return dto;
    }
}
