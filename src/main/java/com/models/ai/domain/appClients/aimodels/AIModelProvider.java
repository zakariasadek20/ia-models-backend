package com.models.ai.domain.appClients.aimodels;

import com.models.ai.domain.appClients.AppClientId;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AIModelProvider {

    Optional<AiModel> getByName(String name);

    Optional<AiModel> getByCommandLine(String commandLine);

    Optional<AiModel> getBy(AiModelId aiModelId);

    List<AiModel> getBy(AppClientId appClientId);

    List<AiModel> getByAppClientIdIn(Collection<AppClientId> appClientIds);

    AiModel save(AiModel aiModel);

    List<AiModel> saveAll(List<AiModel> aiModels);

    List<AiModel> getBy(Collection<AiModelId> aiModelIds);

    List<AiModel> getAll();
}
