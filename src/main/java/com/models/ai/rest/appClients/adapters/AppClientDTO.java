package com.models.ai.rest.appClients.adapters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.ai.domain.appClients.AppClient;
import com.models.ai.rest.appClients.aimodels.adapters.AiModelDTO;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class AppClientDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("host")
    private String host;

    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("store")
    private String store;

    @JsonProperty("last_access")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastAccess;

    @JsonProperty("ai_models")
    private List<AiModelDTO> aiModelsDtos;

    public static AppClientDTO from(AppClient appClient) {
        return builder()
                .id(appClient.getId().getValue())
                .name(appClient.getName())
                .host(appClient.getHost())
                .store(appClient.getStore())
                .lastAccess(appClient.getLastAccess())
                .aiModelsDtos(AiModelDTO.from(appClient.getAiModels()))
                .build();
    }

    public static List<AppClientDTO> from(List<AppClient> appClients) {
        if (appClients == null) {
            return Collections.emptyList();
        }
        return appClients.stream().map(AppClientDTO::from).collect(Collectors.toList());
    }
}
