package com.models.ai.domain.appClients.aimodels.aiimage;

import com.models.ai.domain.appClients.AppClient;
import com.models.ai.domain.appClients.AppClientId;
import com.models.ai.domain.appClients.aimodels.AiModel;
import com.models.ai.domain.appClients.aimodels.AiModelId;
import com.models.ai.domain.base.BaseAuditedEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ai_image")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@EqualsAndHashCode(
        callSuper = true,
        of = {})
@SuperBuilder
public class AiImage extends BaseAuditedEntity<AiImageId> {

    @Column(name = "original_filename")
    private String originalFilename;

    @Column(name = "file_id")
    private String fileId;

    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "value", column = @Column(name = "ai_model_id"))})
    private AiModelId aiModelId;

    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "value", column = @Column(name = "app_client_id"))})
    private AppClientId appClientId;

    @Transient
    private AppClient appClient;

    @Transient
    private AiModel aiModel;

    private AiImage() {
        this.id = new AiImageId();
    }

    public void setAppClient(AppClient appClient) {
        this.appClient = appClient;
    }

    public void setAiModel(AiModel aiModel) {
        this.aiModel = aiModel;
    }
}
