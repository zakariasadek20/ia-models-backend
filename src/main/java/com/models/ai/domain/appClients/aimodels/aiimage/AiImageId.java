package com.models.ai.domain.appClients.aimodels.aiimage;

import com.models.ai.domain.base.Identity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Embeddable
@EqualsAndHashCode(callSuper = false)
public class AiImageId extends Identity {

    @Column(name = "id")
    private String value;

    public AiImageId() {
        super();
        this.value = UUID.randomUUID().toString();
    }

    private AiImageId(String value) {
        super();
        this.value = value;
    }

    public static AiImageId from(String value) {
        return new AiImageId(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
