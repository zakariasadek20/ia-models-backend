package com.models.ai.domain.referentiel.entitylabels;

import com.models.ai.domain.base.BaseEntity;
import com.models.ai.domain.common.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "entity_label")
@Getter
@ToString
@Immutable
public class EntityLabel extends BaseEntity<EntityLabelId> {

    @Column(name = "entity_type")
    @Enumerated(EnumType.STRING)
    private EntityLabelType entityType;

    @Column(name = "entity_id")
    private String entityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @Column(name = "label")
    private String label;

    public static EntityLabel from(EntityLabelType entityType, String entityId, Language language, String label) {

        EntityLabel entityLabel = new EntityLabel();
        entityLabel.id = new EntityLabelId();
        entityLabel.entityType = entityType;
        entityLabel.entityId = entityId;
        entityLabel.language = language;
        entityLabel.label = label;

        return entityLabel;
    }
}
