package com.models.ai.domain.appClients.aimodels;

import com.models.ai.domain.base.Identity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Embeddable
@EqualsAndHashCode(callSuper = false)
public class AiModelId extends Identity {

    @Column(name = "id")
    private String value;

    public AiModelId() {
        super();
        this.value = UUID.randomUUID().toString();
    }

    private AiModelId(String value) {
        super();
        this.value = value;
    }

    public static AiModelId from(String value) {
        return new AiModelId(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
