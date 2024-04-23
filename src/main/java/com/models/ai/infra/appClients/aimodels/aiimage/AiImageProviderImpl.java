package com.models.ai.infra.appClients.aimodels.aiimage;

import com.models.ai.domain.appClients.AppClient;
import com.models.ai.domain.appClients.AppClientId;
import com.models.ai.domain.appClients.AppClientProvider;
import com.models.ai.domain.appClients.aimodels.AIModelProvider;
import com.models.ai.domain.appClients.aimodels.AiModel;
import com.models.ai.domain.appClients.aimodels.AiModelId;
import com.models.ai.domain.appClients.aimodels.aiimage.AiImage;
import com.models.ai.domain.appClients.aimodels.aiimage.AiImageId;
import com.models.ai.domain.appClients.aimodels.aiimage.AiImageProvider;
import com.models.ai.domain.appClients.aimodels.exceptions.AiModelNotFoundException;
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
public class AiImageProviderImpl implements AiImageProvider {

    private final AiImageRepository aiImageRepository;
    private final AppClientProvider appClientProvider;
    private final AIModelProvider aiModelProvider;

    @Override
    public Optional<AiImage> getByOriginalFileName(String originalFileName) {
        Optional<AiImage> aiImageOptional = aiImageRepository.findOneByOriginalFilename(originalFileName);
        aiImageOptional.ifPresent(aiImage -> {
            AppClient appClient =
                    appClientProvider.getBy(aiImage.getAppClientId()).orElseThrow(AppClientsNotFoundException::new);

            AiModel aiModel = aiModelProvider.getBy(aiImage.getAiModelId()).orElseThrow(AiModelNotFoundException::new);
            aiImage.setAppClient(appClient);
            aiImage.setAiModel(aiModel);
        });
        return aiImageOptional;
    }

    @Override
    public Optional<AiImage> getByFileId(String fileId) {
        Optional<AiImage> aiImageOptional = aiImageRepository.findOneByFileId(fileId);
        aiImageOptional.ifPresent(aiImage -> {
            AppClient appClient =
                    appClientProvider.getBy(aiImage.getAppClientId()).orElseThrow(AppClientsNotFoundException::new);

            AiModel aiModel = aiModelProvider.getBy(aiImage.getAiModelId()).orElseThrow(AiModelNotFoundException::new);
            aiImage.setAppClient(appClient);
            aiImage.setAiModel(aiModel);
        });
        return aiImageOptional;
    }

    @Override
    public Optional<AiImage> getBy(AiImageId aiImageId) {
        Optional<AiImage> aiImageOptional = aiImageRepository.findById(aiImageId);
        aiImageOptional.ifPresent(aiImage -> {
            AppClient appClient =
                    appClientProvider.getBy(aiImage.getAppClientId()).orElseThrow(AppClientsNotFoundException::new);

            AiModel aiModel = aiModelProvider.getBy(aiImage.getAiModelId()).orElseThrow(AiModelNotFoundException::new);
            aiImage.setAppClient(appClient);
            aiImage.setAiModel(aiModel);
        });
        return aiImageOptional;
    }

    @Override
    public List<AiImage> getBy(AppClientId appClientId) {

        List<AiImage> aiImages = Optional.ofNullable(aiImageRepository.findAllByAppClientId(appClientId))
                .orElse(Collections.emptyList());
        if (!aiImages.isEmpty()) {
            AppClient appClient = appClientProvider.getBy(appClientId).orElseThrow(AppClientsNotFoundException::new);
            List<AiModelId> aiModelIds =
                    aiImages.stream().map(AiImage::getAiModelId).collect(Collectors.toList());
            Map<AiModelId, AiModel> aiModelMap =
                    aiModelProvider.getBy(aiModelIds).stream().collect(Collectors.toMap(AiModel::getId, ai -> ai));

            aiImages.forEach(aiImage -> {
                AiModel aiModel = Optional.ofNullable(aiModelMap.getOrDefault(aiImage.getAiModelId(), null))
                        .orElseThrow(AiModelNotFoundException::new);
                aiModel.setAppClient(appClient);
                aiImage.setAiModel(aiModel);
            });
        }
        return aiImages;
    }

    @Override
    public List<AiImage> getBy(AiModelId aiModelId) {
        List<AiImage> aiImages = Optional.ofNullable(aiImageRepository.findAllByAiModelId(aiModelId))
                .orElse(Collections.emptyList());
        if (!aiImages.isEmpty()) {
            AiModel aiModel = aiModelProvider.getBy(aiModelId).orElseThrow(AiModelNotFoundException::new);
            List<AppClientId> appClientIds =
                    aiImages.stream().map(AiImage::getAppClientId).collect(Collectors.toList());
            Map<AppClientId, AppClient> appClientMap = appClientProvider.getBy(appClientIds).stream()
                    .collect(Collectors.toMap(AppClient::getId, ai -> ai));

            aiImages.forEach(aiImage -> {
                AppClient appClient = Optional.ofNullable(appClientMap.getOrDefault(aiImage.getAppClientId(), null))
                        .orElseThrow(AppClientsNotFoundException::new);
                aiModel.setAppClient(appClient);
                aiImage.setAiModel(aiModel);
            });
        }
        return aiImages;
    }

    @Override
    public List<AiImage> getByAppClientIdIn(Collection<AppClientId> appClientIds) {
        List<AiImage> aiImages = Optional.ofNullable(aiImageRepository.findAllByAppClientIdIn(appClientIds))
                .orElse(Collections.emptyList());
        if (!aiImages.isEmpty()) {

            Map<AppClientId, AppClient> appClientMap = appClientProvider.getBy(appClientIds).stream()
                    .collect(Collectors.toMap(AppClient::getId, app -> app));

            List<AiModelId> aiModelIds =
                    aiImages.stream().map(AiImage::getAiModelId).collect(Collectors.toList());
            Map<AiModelId, AiModel> aiModelMap =
                    aiModelProvider.getBy(aiModelIds).stream().collect(Collectors.toMap(AiModel::getId, ai -> ai));

            aiImages.forEach(aiImage -> {
                AiModel aiModel = Optional.ofNullable(aiModelMap.getOrDefault(aiImage.getAiModelId(), null))
                        .orElseThrow(AiModelNotFoundException::new);
                AppClient appClient = Optional.ofNullable(appClientMap.getOrDefault(aiImage.getAppClientId(), null))
                        .orElseThrow(AppClientsNotFoundException::new);
                aiModel.setAppClient(appClient);
                aiImage.setAiModel(aiModel);
            });
        }
        return aiImages;
    }

    @Override
    public List<AiImage> getByAiModelIdIn(Collection<AiModelId> aiModelIds) {
        List<AiImage> aiImages = Optional.ofNullable(aiImageRepository.findAllByAiModelIdIn(aiModelIds))
                .orElse(Collections.emptyList());
        if (!aiImages.isEmpty()) {

            Map<AiModelId, AiModel> aiModelMap =
                    aiModelProvider.getBy(aiModelIds).stream().collect(Collectors.toMap(AiModel::getId, ai -> ai));

            List<AppClientId> appClientIds =
                    aiImages.stream().map(AiImage::getAppClientId).collect(Collectors.toList());
            Map<AppClientId, AppClient> appClientMap = appClientProvider.getBy(appClientIds).stream()
                    .collect(Collectors.toMap(AppClient::getId, ai -> ai));

            aiImages.forEach(aiImage -> {
                AiModel aiModel = Optional.ofNullable(aiModelMap.getOrDefault(aiImage.getAiModelId(), null))
                        .orElseThrow(AiModelNotFoundException::new);
                AppClient appClient = Optional.ofNullable(appClientMap.getOrDefault(aiImage.getAppClientId(), null))
                        .orElseThrow(AppClientsNotFoundException::new);
                aiModel.setAppClient(appClient);
                aiImage.setAiModel(aiModel);
            });
        }
        return aiImages;
    }

    @Override
    public AiImage save(AiImage aiImage) {
        AiImage saved = aiImageRepository.save(aiImage);
        AppClient appClient =
                appClientProvider.getBy(saved.getAppClientId()).orElseThrow(AppClientsNotFoundException::new);

        AiModel aiModel = aiModelProvider.getBy(saved.getAiModelId()).orElseThrow(AiModelNotFoundException::new);
        saved.setAppClient(appClient);
        saved.setAiModel(aiModel);

        return saved;
    }

    @Override
    public List<AiImage> saveAll(List<AiImage> aiImages) {
        List<AiImage> aiImageList =
                Optional.ofNullable(aiImageRepository.saveAll(aiImages)).orElse(Collections.emptyList());
        if (!aiImageList.isEmpty()) {

            List<AiModelId> aiModelIds =
                    aiImageList.stream().map(AiImage::getAiModelId).collect(Collectors.toList());
            Map<AiModelId, AiModel> aiModelMap =
                    aiModelProvider.getBy(aiModelIds).stream().collect(Collectors.toMap(AiModel::getId, ai -> ai));

            List<AppClientId> appClientIds =
                    aiImageList.stream().map(AiImage::getAppClientId).collect(Collectors.toList());
            Map<AppClientId, AppClient> appClientMap = appClientProvider.getBy(appClientIds).stream()
                    .collect(Collectors.toMap(AppClient::getId, ai -> ai));

            aiImageList.forEach(aiImage -> {
                AiModel aiModel = Optional.ofNullable(aiModelMap.getOrDefault(aiImage.getAiModelId(), null))
                        .orElseThrow(AiModelNotFoundException::new);
                AppClient appClient = Optional.ofNullable(appClientMap.getOrDefault(aiImage.getAppClientId(), null))
                        .orElseThrow(AppClientsNotFoundException::new);
                aiModel.setAppClient(appClient);
                aiImage.setAiModel(aiModel);
            });
        }
        return aiImageList;
    }

    @Override
    public List<AiImage> getBy(Collection<AiImageId> aiImageIds) {
        List<AiImage> aiImageList =
                Optional.ofNullable(aiImageRepository.findAllById(aiImageIds)).orElse(Collections.emptyList());
        if (!aiImageList.isEmpty()) {

            List<AiModelId> aiModelIds =
                    aiImageList.stream().map(AiImage::getAiModelId).collect(Collectors.toList());
            Map<AiModelId, AiModel> aiModelMap =
                    aiModelProvider.getBy(aiModelIds).stream().collect(Collectors.toMap(AiModel::getId, ai -> ai));

            List<AppClientId> appClientIds =
                    aiImageList.stream().map(AiImage::getAppClientId).collect(Collectors.toList());
            Map<AppClientId, AppClient> appClientMap = appClientProvider.getBy(appClientIds).stream()
                    .collect(Collectors.toMap(AppClient::getId, ai -> ai));

            aiImageList.forEach(aiImage -> {
                AiModel aiModel = Optional.ofNullable(aiModelMap.getOrDefault(aiImage.getAiModelId(), null))
                        .orElseThrow(AiModelNotFoundException::new);
                AppClient appClient = Optional.ofNullable(appClientMap.getOrDefault(aiImage.getAppClientId(), null))
                        .orElseThrow(AppClientsNotFoundException::new);
                aiModel.setAppClient(appClient);
                aiImage.setAiModel(aiModel);
            });
        }
        return aiImageList;
    }

    @Override
    public List<AiImage> getAll() {
        List<AiImage> aiImageList =
                Optional.ofNullable(aiImageRepository.findAll()).orElse(Collections.emptyList());
        if (!aiImageList.isEmpty()) {

            List<AiModelId> aiModelIds =
                    aiImageList.stream().map(AiImage::getAiModelId).collect(Collectors.toList());
            Map<AiModelId, AiModel> aiModelMap =
                    aiModelProvider.getBy(aiModelIds).stream().collect(Collectors.toMap(AiModel::getId, ai -> ai));

            List<AppClientId> appClientIds =
                    aiImageList.stream().map(AiImage::getAppClientId).collect(Collectors.toList());
            Map<AppClientId, AppClient> appClientMap = appClientProvider.getBy(appClientIds).stream()
                    .collect(Collectors.toMap(AppClient::getId, ai -> ai));

            aiImageList.forEach(aiImage -> {
                AiModel aiModel = Optional.ofNullable(aiModelMap.getOrDefault(aiImage.getAiModelId(), null))
                        .orElseThrow(AiModelNotFoundException::new);
                AppClient appClient = Optional.ofNullable(appClientMap.getOrDefault(aiImage.getAppClientId(), null))
                        .orElseThrow(AppClientsNotFoundException::new);
                aiModel.setAppClient(appClient);
                aiImage.setAiModel(aiModel);
            });
        }
        return aiImageList;
    }
}
