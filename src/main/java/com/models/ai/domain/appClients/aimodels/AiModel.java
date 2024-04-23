package com.models.ai.domain.appClients.aimodels;

import com.models.ai.domain.appClients.AppClient;
import com.models.ai.domain.appClients.AppClientId;
import com.models.ai.domain.appClients.aimodels.commands.AiModelAddCommand;
import com.models.ai.domain.base.BaseAuditedEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ai_models")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@EqualsAndHashCode(
        callSuper = true,
        of = {})
public class AiModel extends BaseAuditedEntity<AiModelId> {

    @Column(name = "name")
    private String name;

    @Column(name = "command_line")
    private String commandLine;

    @Column(name = "ai_folder")
    private String aiFolder;

    @Column(name = "directory_input_folder")
    private String directoryInputFolder;

    @Column(name = "directory_output_folder")
    private String directoryOutputFolder;

    @Column(name = "full_output_folder")
    private String fullOutputFolder;

    @Column(name = "python_folder")
    private String python_folder;

    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "value", column = @Column(name = "app_client_id"))})
    private AppClientId appClientId;

    @Transient
    private AppClient appClient;

    private AiModel() {
        this.id = new AiModelId();
    }

    public static AiModel from(AiModelAddCommand command) {
        AiModel aiModel = new AiModel();
        aiModel.name = command.getName();
        aiModel.commandLine = command.getCommandLine();
        aiModel.lastAccess = LocalDateTime.now();
        return aiModel;
    }

    public void setAppClient(AppClient appClient) {
        this.appClient = appClient;
    }
}
