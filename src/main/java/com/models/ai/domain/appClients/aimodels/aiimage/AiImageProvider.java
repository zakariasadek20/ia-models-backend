package com.models.ai.domain.appClients.aimodels.aiimage;

import com.models.ai.domain.appClients.AppClientId;
import com.models.ai.domain.appClients.aimodels.AiModelId;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AiImageProvider {

    Optional<AiImage> getByOriginalFileName(String originalFileName);

    Optional<AiImage> getByFileId(String fileId);

    Optional<AiImage> getBy(AiImageId aiImageId);

    List<AiImage> getBy(AppClientId appClientId);

    List<AiImage> getBy(AiModelId aiModelId);

    List<AiImage> getByAppClientIdIn(Collection<AppClientId> appClientIds);

    List<AiImage> getByAiModelIdIn(Collection<AiModelId> aiModelIds);

    AiImage save(AiImage aiImage);

    List<AiImage> saveAll(List<AiImage> aiImages);

    List<AiImage> getBy(Collection<AiImageId> aiImageIds);

    List<AiImage> getAll();
}
