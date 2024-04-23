package com.models.ai.infra.appClients;

import com.models.ai.domain.appClients.AppClient;
import com.models.ai.domain.appClients.AppClientId;
import com.models.ai.domain.appClients.AppClientProvider;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AppClientProviderImpl implements AppClientProvider {

    private final AppClientRepository appClientRepository;
    //    private final AIModelProvider aiModelProvider;

    @Override
    public Optional<AppClient> getByName(String name) {
        Optional<AppClient> appClient = appClientRepository.findOneByName(name);
        //        appClient.ifPresent(appClient1 -> {
        //            List<AiModel> aiModels=aiModelProvider.getBy(appClient1.getId());
        //            appClient1.setAiModels(aiModels);
        //        });
        return appClient;
    }

    @Override
    public Optional<AppClient> getBy(AppClientId appClientId) {
        Optional<AppClient> appClient = appClientRepository.findById(appClientId);
        //        appClient.ifPresent(appClient1 -> {
        //            List<AiModel> aiModels=aiModelProvider.getBy(appClient1.getId());
        //            appClient1.setAiModels(aiModels);
        //        });
        return appClient;
    }

    @Override
    public Optional<AppClient> getByHost(String host) {
        Optional<AppClient> appClient = appClientRepository.findOneByHost(host);
        //        appClient.ifPresent(appClient1 -> {
        //            List<AiModel> aiModels=aiModelProvider.getBy(appClient1.getId());
        //            appClient1.setAiModels(aiModels);
        //        });
        return appClient;
    }

    @Override
    public List<AppClient> getByStore(String store) {
        List<AppClient> appClients =
                Optional.ofNullable(appClientRepository.findAllByStore(store)).orElse(Collections.emptyList());
        //        if(!appClients.isEmpty()){
        //            List<AppClientId> appClientIds =
        // appClients.stream().map(AppClient::getId).collect(Collectors.toList());
        //            Map<AppClientId, List<AiModel>> aiModelMap =
        // aiModelProvider.getByAppClientIdIn(appClientIds).stream().collect(Collectors.groupingBy(AiModel::getAppClientId));
        //
        //            appClients.forEach(appClient -> {
        //                List<AiModel> aiModelList = aiModelMap.getOrDefault(appClient.getId(),
        // Collections.emptyList());
        //                if(!aiModelList.isEmpty()){
        //                    appClient.setAiModels(aiModelList);
        //                }
        //            });
        //        }
        return appClients;
    }

    @Override
    public AppClient save(AppClient appClient) {
        return appClientRepository.save(appClient);
    }

    @Override
    public List<AppClient> saveAll(List<AppClient> appClients) {
        return Optional.ofNullable(appClientRepository.saveAll(appClients)).orElse(Collections.emptyList());
    }

    @Override
    public List<AppClient> getBy(Collection<AppClientId> appClientIds) {
        List<AppClient> appClients = Optional.ofNullable(appClientRepository.findAllById(appClientIds))
                .orElse(Collections.emptyList());
        //        if(!appClients.isEmpty()){
        //            Map<AppClientId, List<AiModel>> aiModelMap =
        // aiModelProvider.getByAppClientIdIn(appClientIds).stream().collect(Collectors.groupingBy(AiModel::getAppClientId));
        //
        //            appClients.forEach(appClient -> {
        //                List<AiModel> aiModelList = aiModelMap.getOrDefault(appClient.getId(),
        // Collections.emptyList());
        //                if(!aiModelList.isEmpty()){
        //                    appClient.setAiModels(aiModelList);
        //                }
        //            });
        //        }
        return appClients;
    }

    @Override
    public List<AppClient> getAll() {
        List<AppClient> appClients =
                Optional.ofNullable(appClientRepository.findAll()).orElse(Collections.emptyList());
        //        if(!appClients.isEmpty()){
        //            List<AppClientId> appClientIds =
        // appClients.stream().map(AppClient::getId).collect(Collectors.toList());
        //            Map<AppClientId, List<AiModel>> aiModelMap =
        // aiModelProvider.getByAppClientIdIn(appClientIds).stream().collect(Collectors.groupingBy(AiModel::getAppClientId));
        //
        //            appClients.forEach(appClient -> {
        //                List<AiModel> aiModelList = aiModelMap.getOrDefault(appClient.getId(),
        // Collections.emptyList());
        //                if(!aiModelList.isEmpty()){
        //                    appClient.setAiModels(aiModelList);
        //                }
        //            });
        //        }
        return appClients;
    }
}
