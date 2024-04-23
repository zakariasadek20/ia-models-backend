package com.models.ai.domain.referentiel.entitylabels;

import com.models.ai.domain.base.Identity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Embeddable
@EqualsAndHashCode(callSuper = false)
public class EntityLabelId extends Identity {

    @Column(name = "id")
    private final String value;

    public EntityLabelId() {
        super();
        this.value = UUID.randomUUID().toString();
    }

    private EntityLabelId(String value) {
        super();
        this.value = value;
    }

    public static EntityLabelId from(String value) {
        return new EntityLabelId(value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
