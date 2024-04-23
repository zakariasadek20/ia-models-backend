package com.models.ai.infra.referentiel.entitylabels;

import com.models.ai.domain.referentiel.entitylabels.EntityLabel;
import com.models.ai.domain.referentiel.entitylabels.EntityLabelProvider;
import com.models.ai.domain.referentiel.entitylabels.EntityLabelType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EntityLabelProviderImpl implements EntityLabelProvider {

    private final EntityLabelRepository entityLabelRepository;

    @Override
    @Modifying
    public void deleteBy(EntityLabelType entityLabelType, String id) {
        entityLabelRepository.deleteBy(entityLabelType, id);
        entityLabelRepository.flush();
    }

    @Override
    public List<EntityLabel> getBy(EntityLabelType entityLabelType, String entityId) {
        return entityLabelRepository.getByEntityTypeAndEntityId(entityLabelType, entityId);
    }

    @Override
    public List<EntityLabel> getBy(EntityLabelType entityLabelType, List<String> entityIds) {
        return Optional.of(entityLabelRepository.getByEntityTypeAndEntityIdIn(entityLabelType, entityIds))
                .orElse(Collections.emptyList());
    }

    @Override
    public List<EntityLabel> save(List<EntityLabel> entityLabels) {
        return entityLabelRepository.saveAll(entityLabels);
    }

    @Override
    public List<EntityLabel> getBy(EntityLabelType entityLabelType) {
        return entityLabelRepository.getByEntityType(entityLabelType);
    }
}
