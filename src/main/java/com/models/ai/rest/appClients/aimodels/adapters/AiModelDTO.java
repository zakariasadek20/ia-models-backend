package com.models.ai.rest.appClients.aimodels.adapters;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.ai.domain.appClients.aimodels.AiModel;
import com.models.ai.rest.appClients.adapters.AppClientDTO;
import jakarta.persistence.*;
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
public class AiModelDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("command_line")
    private String commandLine;

    @JsonProperty("last_access")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastAccess;

    @JsonProperty("app_client_id")
    private String appClientId;

    @JsonProperty("app_client")
    private AppClientDTO appClientDtos;

    public static AiModelDTO from(AiModel aiModel) {
        return builder()
                .id(aiModel.getId().getValue())
                .name(aiModel.getName())
                .commandLine(aiModel.getCommandLine())
                .lastAccess(aiModel.getLastAccess())
                .appClientDtos(AppClientDTO.from(aiModel.getAppClient()))
                .build();
    }

    public static List<AiModelDTO> from(List<AiModel> aiModels) {
        if (aiModels == null) {
            return Collections.emptyList();
        }
        return aiModels.stream().map(AiModelDTO::from).collect(Collectors.toList());
    }
}
