package com.models.ai.domain.appClients;

import com.models.ai.domain.appClients.aimodels.AiModel;
import com.models.ai.domain.appClients.commands.AppClientsAddCommand;
import com.models.ai.domain.base.BaseAuditedEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "app_clients")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@EqualsAndHashCode(
        callSuper = true,
        of = {})
public class AppClient extends BaseAuditedEntity<AppClientId> {

    @Column(name = "name")
    private String name;

    @Column(name = "host")
    private String host;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "store")
    private String store;

    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    @Transient
    private List<AiModel> aiModels;

    private AppClient() {
        this.id = new AppClientId();
    }

    public static AppClient from(AppClientsAddCommand command) {
        AppClient appClient = new AppClient();
        appClient.name = command.getName();
        appClient.host = command.getHost();
        appClient.lastAccess = LocalDateTime.now();
        return appClient;
    }

    public void setAiModels(List<AiModel> aiModels) {
        this.aiModels = aiModels;
    }
}
