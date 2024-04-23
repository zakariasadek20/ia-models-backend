package com.models.ai.infra.appClients.aimodels.aiimage;

import com.models.ai.domain.appClients.AppClientId;
import com.models.ai.domain.appClients.aimodels.AiModelId;
import com.models.ai.domain.appClients.aimodels.aiimage.AiImage;
import com.models.ai.domain.appClients.aimodels.aiimage.AiImageId;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiImageRepository extends JpaRepository<AiImage, AiImageId> {

    Optional<AiImage> findOneByOriginalFilename(String originalFileName);

    Optional<AiImage> findOneByFileId(String fileId);

    List<AiImage> findAllByAppClientId(AppClientId appClientId);

    List<AiImage> findAllByAiModelId(AiModelId aiModelId);

    List<AiImage> findAllByAppClientIdIn(Collection<AppClientId> appClientIds);

    List<AiImage> findAllByAiModelIdIn(Collection<AiModelId> aiModelIds);
}
