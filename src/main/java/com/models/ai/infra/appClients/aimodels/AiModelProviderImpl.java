package com.models.ai.infra.appClients.aimodels;

import com.models.ai.domain.appClients.AppClient;
import com.models.ai.domain.appClients.AppClientId;
import com.models.ai.domain.appClients.AppClientProvider;
import com.models.ai.domain.appClients.aimodels.AIModelProvider;
import com.models.ai.domain.appClients.aimodels.AiModel;
import com.models.ai.domain.appClients.aimodels.AiModelId;
import com.models.ai.domain.appClients.exceptions.AppClientsNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AiModelProviderImpl implements AIModelProvider {

    private final AiModelRepository aiModelRepository;
    private final AppClientProvider appClientProvider;

    @Override
    public Optional<AiModel> getByName(String name) {
        Optional<AiModel> aiModel = aiModelRepository.findOneByName(name);
        aiModel.ifPresent(aiModel1 -> {
            AppClient appClient =
                    appClientProvider.getBy(aiModel1.getAppClientId()).orElseThrow(AppClientsNotFoundException::new);
            aiModel1.setAppClient(appClient);
        });
        return aiModel;
    }

    @Override
    public Optional<AiModel> getByCommandLine(String commandLine) {
        Optional<AiModel> aiModel = aiModelRepository.findOneByCommandLine(commandLine);
        aiModel.ifPresent(aiModel1 -> {
            AppClient appClient =
                    appClientProvider.getBy(aiModel1.getAppClientId()).orElseThrow(AppClientsNotFoundException::new);
            aiModel1.setAppClient(appClient);
        });
        return aiModel;
    }

    @Override
    public Optional<AiModel> getBy(AiModelId aiModelId) {
        Optional<AiModel> aiModel = aiModelRepository.findById(aiModelId);
        aiModel.ifPresent(aiModel1 -> {
            AppClient appClient =
                    appClientProvider.getBy(aiModel1.getAppClientId()).orElseThrow(AppClientsNotFoundException::new);
            aiModel1.setAppClient(appClient);
        });
        return aiModel;
    }

    @Override
    public List<AiModel> getBy(AppClientId appClientId) {
        AppClient appClient = appClientProvider.getBy(appClientId).orElseThrow(AppClientsNotFoundException::new);
        List<AiModel> aiModelList = Optional.ofNullable(aiModelRepository.findAllByAppClientId(appClientId))
                .orElse(Collections.emptyList());
        if (!aiModelList.isEmpty()) {
            aiModelList.forEach(aiModel -> aiModel.setAppClient(appClient));
        }
        return aiModelList;
    }

    @Override
    public List<AiModel> getByAppClientIdIn(Collection<AppClientId> appClientIds) {
        List<AiModel> aiModels = Optional.ofNullable(aiModelRepository.findAllByAppClientIdIn(appClientIds))
                .orElse(Collections.emptyList());
        if (!aiModels.isEmpty()) {
            Map<AppClientId, AppClient> appClientMap = appClientProvider.getBy(appClientIds).stream()
                    .collect(Collectors.toMap(AppClient::getId, appClient -> appClient));
            aiModels.forEach(aiModel -> {
                AppClient appClient = Optional.ofNullable(appClientMap.getOrDefault(aiModel.getAppClientId(), null))
                        .orElseThrow(AppClientsNotFoundException::new);
                aiModel.setAppClient(appClient);
            });
        }
        return aiModels;
    }

    @Override
    public AiModel save(AiModel aiModel) {
        AppClient appClient =
                appClientProvider.getBy(aiModel.getAppClientId()).orElseThrow(AppClientsNotFoundException::new);
        AiModel aiModel1 = aiModelRepository.save(aiModel);
        aiModel1.setAppClient(appClient);
        return aiModel1;
    }

    @Override
    public List<AiModel> saveAll(List<AiModel> aiModels) {
        List<AiModel> aiModelList = aiModelRepository.saveAll(aiModels);
        if (!aiModelList.isEmpty()) {
            List<AppClientId> appClientIds =
                    aiModelList.stream().map(AiModel::getAppClientId).collect(Collectors.toList());
            Map<AppClientId, AppClient> appClientMap = appClientProvider.getBy(appClientIds).stream()
                    .collect(Collectors.toMap(AppClient::getId, appClient -> appClient));
            aiModelList.forEach(aiModel -> {
                AppClient appClient = Optional.ofNullable(appClientMap.getOrDefault(aiModel.getAppClientId(), null))
                        .orElseThrow(AppClientsNotFoundException::new);
                aiModel.setAppClient(appClient);
            });
        }
        return aiModelList;
    }

    @Override
    public List<AiModel> getBy(Collection<AiModelId> aiModelIds) {
        List<AiModel> aiModelList =
                Optional.ofNullable(aiModelRepository.findAllById(aiModelIds)).orElse(Collections.emptyList());
        if (!aiModelList.isEmpty()) {
            List<AppClientId> appClientIds =
                    aiModelList.stream().map(AiModel::getAppClientId).collect(Collectors.toList());
            Map<AppClientId, AppClient> appClientMap = appClientProvider.getBy(appClientIds).stream()
                    .collect(Collectors.toMap(AppClient::getId, appClient -> appClient));
            aiModelList.forEach(aiModel -> {
                AppClient appClient = Optional.ofNullable(appClientMap.getOrDefault(aiModel.getAppClientId(), null))
                        .orElseThrow(AppClientsNotFoundException::new);
                aiModel.setAppClient(appClient);
            });
        }
        return aiModelList;
    }

    @Override
    public List<AiModel> getAll() {
        List<AiModel> aiModelList =
                Optional.ofNullable(aiModelRepository.findAll()).orElse(Collections.emptyList());
        if (!aiModelList.isEmpty()) {
            List<AppClientId> appClientIds =
                    aiModelList.stream().map(AiModel::getAppClientId).collect(Collectors.toList());
            Map<AppClientId, AppClient> appClientMap = appClientProvider.getBy(appClientIds).stream()
                    .collect(Collectors.toMap(AppClient::getId, appClient -> appClient));
            aiModelList.forEach(aiModel -> {
                AppClient appClient = Optional.ofNullable(appClientMap.getOrDefault(aiModel.getAppClientId(), null))
                        .orElseThrow(AppClientsNotFoundException::new);
                aiModel.setAppClient(appClient);
            });
        }
        return aiModelList;
    }
}
