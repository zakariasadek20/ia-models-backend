package com.models.ai.infra.appClients.aimodels;

import com.models.ai.domain.appClients.AppClientId;
import com.models.ai.domain.appClients.aimodels.AiModel;
import com.models.ai.domain.appClients.aimodels.AiModelId;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiModelRepository extends JpaRepository<AiModel, AiModelId> {

    Optional<AiModel> findOneByName(String name);

    Optional<AiModel> findOneByCommandLine(String commandLine);

    List<AiModel> findAllByAppClientId(AppClientId appClientId);

    List<AiModel> findAllByAppClientIdIn(Collection<AppClientId> appClientIds);
}
