package com.models.ai.domain.referentiel.entitylabels;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;

public interface EntityLabelProvider {
    @Modifying
    void deleteBy(EntityLabelType entityLabelType, String id);

    List<EntityLabel> getBy(EntityLabelType entityLabelType, String id);

    List<EntityLabel> getBy(EntityLabelType entityLabelType, List<String> id);

    List<EntityLabel> save(List<EntityLabel> entityLabels);

    List<EntityLabel> getBy(EntityLabelType entityLabelType);
}
