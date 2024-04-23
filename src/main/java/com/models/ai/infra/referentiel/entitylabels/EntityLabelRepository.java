package com.models.ai.infra.referentiel.entitylabels;

import com.models.ai.domain.referentiel.entitylabels.EntityLabel;
import com.models.ai.domain.referentiel.entitylabels.EntityLabelId;
import com.models.ai.domain.referentiel.entitylabels.EntityLabelType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityLabelRepository extends JpaRepository<EntityLabel, EntityLabelId> {

    List<EntityLabel> getByEntityTypeAndEntityId(EntityLabelType entityType, String identity);

    List<EntityLabel> getByEntityType(EntityLabelType entityType);

    @Modifying
    @Query("DELETE FROM EntityLabel r WHERE r.entityType =:entity_type  AND r.entityId = :entity_id")
    void deleteBy(@Param("entity_type") EntityLabelType entityLabelType, @Param("entity_id") String identity);

    List<EntityLabel> getByEntityTypeAndEntityIdIn(EntityLabelType entityLabelType, List<String> entityIds);
}
