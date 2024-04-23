package com.models.ai.rest.referentiel.entitylabels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.ai.domain.referentiel.entitylabels.EntityLabel;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class LabelDTO {

    @JsonProperty("label")
    private String label;

    @JsonProperty("placeholder")
    private String placeholder;

    @JsonProperty("info_bulle")
    private String infoBulle;

    @JsonProperty("commentaire")
    private String commentaire;

    public static LabelDTO from(EntityLabel entityLabel) {
        return builder().label(entityLabel.getLabel()).build();
    }

    public static LabelDTO from(String labels, String placeholder, String infoBulle) {
        return builder()
                .label(labels)
                .placeholder(placeholder)
                .infoBulle(infoBulle)
                .build();
    }

    public static Map<String, LabelDTO> toMap(List<EntityLabel> labels) {
        return labels.stream().collect(Collectors.toMap(l -> l.getLanguage().name(), LabelDTO::from));
    }
}
