package com.models.ai.rest.referentiel.entitylabels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.ai.domain.referentiel.entitylabels.EntityLabel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.*;
import org.apache.commons.collections4.ListUtils;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class EntityLabelDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("entity_type")
    private String entityType;

    @JsonProperty("entity_id")
    private String entityId;

    @JsonProperty("language")
    private String language;

    @JsonProperty("label")
    private String label;

    private static EntityLabelDTO from(EntityLabel entityLabel) {
        return builder()
                .id(entityLabel.getId().getValue())
                .entityType(entityLabel.getEntityType().name())
                .entityId(entityLabel.getEntityId())
                .label(entityLabel.getLabel())
                .language(entityLabel.getLanguage().name())
                .build();
    }

    public static List<EntityLabelDTO> from(List<EntityLabel> entityLabels) {

        List<EntityLabelDTO> labelDTOS = new ArrayList<>();

        if (entityLabels != null) {

            labelDTOS = entityLabels.stream().map(EntityLabelDTO::from).collect(Collectors.toList());
        }

        return ListUtils.defaultIfNull(labelDTOS, new ArrayList<>());
    }
}
